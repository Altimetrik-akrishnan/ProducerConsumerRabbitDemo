package com.onlineshop.products.catalogue.service;

import com.onlineshop.products.catalogue.dto.ProductDto;

import java.util.List;

public interface CatalogueService {
    List<ProductDto> getAllProductDetails();

    ProductDto getProductDetailsBasedOnId(Integer id);

    /*void deleteProductDetails(ProductIdentifierDto productDto);*/
}
