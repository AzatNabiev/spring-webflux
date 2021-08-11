package ru.itis.javalab.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.itis.javalab.dto.ProductDto;

public interface ProductService {

    Flux<ProductDto> getProducts();
    Mono<ProductDto> getProduct(String id);
    Flux<ProductDto> getProductInRange(double min, double max);
    Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono);
    Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono,String id);
    Mono<Void> deleteProduct(String id);
}
