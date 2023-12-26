package com.onlineshop.products.catalogue.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.products.catalogue.constants.CatalogueConstants;
import com.onlineshop.products.catalogue.dto.ProductDto;
import com.onlineshop.products.catalogue.entity.Product;
import com.onlineshop.products.catalogue.exception.ProductNotFoundException;
import com.onlineshop.products.catalogue.mapper.ProductMapper;
import com.onlineshop.products.catalogue.repository.ProductRepository;
import com.onlineshop.products.catalogue.service.CatalogueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<ProductDto> getAllProductDetails() {
        List<ProductDto> productDtoList = productRepository.findAll().stream().map(ProductMapper.MAPPER::mapToProductDto).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(productDtoList)) {
            productDtoList = Arrays.asList(new ProductDto(1, "Iphone", "Apple", 2.4f), new ProductDto(2, "Galaxy", "Samsung", 3.3f));
            productDtoList.stream().forEach(product-> {
                Product productEntity = ProductMapper.MAPPER.mapToProduct(product);
                productRepository.save(productEntity);
            });
        }
        return productDtoList;
    }

    @Override
    public ProductDto getProductDetailsBasedOnId(Integer productId) {
        return productRepository.findByProductId(productId).map(product->{
                    try {
                        rabbitTemplate.convertAndSend(CatalogueConstants.MQ_NAME, objectMapper.writeValueAsString(product));
                        log.info("Successfully pushed the message to RABBIT MQ");
                    } catch (JsonProcessingException e) {
                        log.error("Exception while converting fetched product data into JSON " + e.getMessage());
                    }
                    return ProductMapper.MAPPER.mapToProductDto(product);
                })
                .orElseThrow(categoryNotFoundExceptionSupplier(productId));
    }

    private Supplier<ProductNotFoundException> categoryNotFoundExceptionSupplier(int productId){
        return ()-> new ProductNotFoundException("Fetching product item -", "Item with the id:" + productId + " is not present in DB" , CatalogueConstants.ERROR_CODE_CATEGORY_NOT_EXIST);
    }
}
