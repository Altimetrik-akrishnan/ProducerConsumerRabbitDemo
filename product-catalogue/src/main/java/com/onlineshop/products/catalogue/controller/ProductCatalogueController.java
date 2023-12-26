package com.onlineshop.products.catalogue.controller;

import com.onlineshop.products.catalogue.dto.ProductDto;
import com.onlineshop.products.catalogue.service.CatalogueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductCatalogueController {

    @Autowired
    CatalogueService catalogueService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductDetails(){
       return new ResponseEntity<List<ProductDto>>(catalogueService.getAllProductDetails(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductDetailsBasedOnId(@PathVariable("id") Integer productId){
        return new ResponseEntity<ProductDto>(catalogueService.getProductDetailsBasedOnId(productId), HttpStatus.OK);
    }
}
