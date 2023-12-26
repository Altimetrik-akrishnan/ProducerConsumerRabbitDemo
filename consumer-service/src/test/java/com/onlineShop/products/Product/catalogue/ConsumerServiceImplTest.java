package com.onlineshop.consumer.catalogue.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.consumer.catalogue.client.ProductCatalogueApi;
import com.onlineshop.consumer.catalogue.entity.Product;
import com.onlineshop.consumer.catalogue.repository.ConsumerRepository;
import com.onlineshop.consumer.catalogue.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsumerServiceImplTest {

    @Mock
    private ProductCatalogueApi productCatalogueApi;

    @Mock
    private ConsumerRepository consumerRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductDetails() {
        // Arrange
        List<ProductDto> expectedProducts = Collections.singletonList(new ProductDto(/* Set your values here */));
        when(productCatalogueApi.fetchAllProductDetails()).thenReturn(expectedProducts);

        // Act
        List<ProductDto> result = consumerService.getAllProductDetails();

        // Assert
        assertEquals(expectedProducts, result);
        verify(productCatalogueApi, times(1)).fetchAllProductDetails();
    }

    @Test
    void getProductDetailsBasedOnId() {
        // Arrange
        Integer productId = 1;
        ProductDto expectedProduct = new ProductDto(/* Set your values here */);
        when(productCatalogueApi.getProductDetailsBasedOnId(productId)).thenReturn(expectedProduct);

        // Act
        ProductDto result = consumerService.getProductDetailsBasedOnId(productId);

        // Assert
        assertEquals(expectedProduct, result);
        verify(productCatalogueApi, times(1)).getProductDetailsBasedOnId(productId);
    }

    @Test
    void receiveMessage() throws Exception {
        // Arrange
        String message = "{\"productId\": 1, \"productName\": \"TestProduct\"}";
        Product expectedProduct = new Product(1, "TestProduct", "TestProduct", 4.4f);
        when(objectMapper.readValue(message, Product.class)).thenReturn(expectedProduct);

        // Act
        consumerService.receiveMessage(message);

        // Assert
        verify(consumerRepository, times(1)).save(expectedProduct);
    }
}
