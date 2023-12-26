package com.onlineshop.consumer.catalogue.service;

import com.onlineshop.consumer.catalogue.dto.ProductDto;

import java.util.List;

public interface ConsumerService {
    List<ProductDto> getAllProductDetails();

    ProductDto getProductDetailsBasedOnId(Integer id);

    /*void deleteProductDetails(ProductIdentifierDto productDto);*/
}
