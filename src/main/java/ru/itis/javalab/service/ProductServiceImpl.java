package ru.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.javalab.dto.ProductDto;
import ru.itis.javalab.repository.ProductRepository;
import ru.itis.javalab.utils.ProductUtils;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Flux<ProductDto> getProducts() {
        return repository.findAll().map(ProductUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> getProduct(String id) {
        return repository.findById(id).map(ProductUtils::entityToDto);
    }

    @Override
    public Flux<ProductDto> getProductInRange(double min, double max) {
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    @Override
    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(ProductUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(ProductUtils::entityToDto);
    }

    @Override
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p->productDtoMono.map(ProductUtils::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(repository::save)
                .map(ProductUtils::entityToDto);
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return repository.deleteById(id);
    }
}
