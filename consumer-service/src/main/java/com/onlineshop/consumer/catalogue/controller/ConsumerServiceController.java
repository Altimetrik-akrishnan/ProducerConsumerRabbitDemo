package com.onlineshop.consumer.catalogue.controller;

import com.onlineshop.consumer.catalogue.dto.ProductDto;
import com.onlineshop.consumer.catalogue.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consume")
@Slf4j
public class ConsumerServiceController {

    @Autowired
    ConsumerService consumerService;

    @GetMapping("productList")
    public ResponseEntity<List<ProductDto>> getAllProductDetails(){
       return new ResponseEntity<List<ProductDto>>(consumerService.getAllProductDetails(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductDetailsBasedOnId(@PathVariable("id") Integer productId){
        return new ResponseEntity<ProductDto>(consumerService.getProductDetailsBasedOnId(productId), HttpStatus.OK);
    }
}
