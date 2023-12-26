package com.onlineshop.consumer.catalogue.client;

import com.onlineshop.consumer.catalogue.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "PRODUCT-SERVICE")
public interface ProductCatalogueApi {

    @GetMapping("/products")
    List<ProductDto> fetchAllProductDetails();

    @GetMapping("/products/product/{id}")
    ProductDto getProductDetailsBasedOnId(@PathVariable("id") Integer productId);
}
