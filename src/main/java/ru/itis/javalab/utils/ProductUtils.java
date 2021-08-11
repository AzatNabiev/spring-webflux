package ru.itis.javalab.utils;

import org.springframework.beans.BeanUtils;
import ru.itis.javalab.dto.ProductDto;
import ru.itis.javalab.model.Product;

public class ProductUtils {
    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
