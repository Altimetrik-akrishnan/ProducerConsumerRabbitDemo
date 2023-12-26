package com.onlineShop.products.Product.catalogue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineshop.products.catalogue.dto.ProductDto;
import com.onlineshop.products.catalogue.entity.Product;
import com.onlineshop.products.catalogue.exception.ProductNotFoundException;
import com.onlineshop.products.catalogue.repository.ProductRepository;
import com.onlineshop.products.catalogue.service.impl.CatalogueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CatalogueServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CatalogueServiceImpl catalogueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductDetails() throws JsonProcessingException {
        // Arrange
        List<ProductDto> expectedProducts = Arrays.asList(
                new ProductDto(1, "Iphone", "Apple", 4.4f),
                new ProductDto(2, "Galaxy", "Samsung", 4.2f)
        );
        when(productRepository.findAll()).thenReturn(Arrays.asList(new Product(1, "Iphone", "Apple", 4.4f), new Product(2, "Galaxy", "Samsung", 4.2f)));
        //when(objectMapper.writeValueAsString(any())).thenReturn(""); // Mocking JSON conversion

        // Act
        List<ProductDto> result = catalogueService.getAllProductDetails();

        // Assert
        assertEquals(expectedProducts.get(0).getProductId(), result.get(0).getProductId());
        //verify(productRepository, times(1)).findAll();
        //verify(objectMapper, times(2)).writeValueAsString(any());
    }

    @Test
    void getProductDetailsBasedOnId() throws JsonProcessingException {
        // Arrange
        int productId = 1;
        ProductDto expectedProduct = new ProductDto(2, "Galaxy", "Samsung", 4.2f);
        Product mockProduct = new Product(2, "Galaxy", "Samsung", 4.2f);
        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(mockProduct));
        //when(objectMapper.writeValueAsString(mockProduct)).thenReturn(""); // Mocking JSON conversion

        // Act
        ProductDto result = catalogueService.getProductDetailsBasedOnId(productId);

        // Assert
        assertEquals(expectedProduct.getProductId(), result.getProductId());
        //verify(productRepository, times(1)).findByProductId(productId);
        //verify(rabbitTemplate, times(1)).convertAndSend(eq("test-queue"), anyString());
        //verify(objectMapper, times(1)).writeValueAsString(mockProduct);
    }

    @Test
    void getProductDetailsBasedOnIdNotFound() {
        // Arrange
        int productId = 1;
        when(productRepository.findByProductId(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductNotFoundException.class, () -> catalogueService.getProductDetailsBasedOnId(productId));
        verify(productRepository, times(1)).findByProductId(productId);
    }
}
