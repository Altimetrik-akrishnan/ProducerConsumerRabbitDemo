package com.onlineshop.consumer.catalogue.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.consumer.catalogue.client.ProductCatalogueApi;
import com.onlineshop.consumer.catalogue.constants.CatalogueConstants;
import com.onlineshop.consumer.catalogue.entity.Product;
import com.onlineshop.consumer.catalogue.repository.ConsumerRepository;
import com.onlineshop.consumer.catalogue.service.ConsumerService;
import com.onlineshop.consumer.catalogue.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ProductCatalogueApi productCatalogueApi;

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<ProductDto> getAllProductDetails() {
       return productCatalogueApi.fetchAllProductDetails();
    }

    @Override
    public ProductDto getProductDetailsBasedOnId(Integer productId) {
        return productCatalogueApi.getProductDetailsBasedOnId(productId);
    }

    @RabbitListener(queues = CatalogueConstants.MQ_NAME)
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        try {
            Product product = objectMapper.readValue(message, Product.class);
            consumerRepository.save(product);
            log.info("Successfully save message into consumer table");
        } catch (JsonProcessingException e) {
            log.error("Exception while converting message into entity: " + e.getMessage());
        }
        // Process the message as needed
    }
}
