package com.onlineshop.consumer.catalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotEmpty(message = "Product id cannot be empty")
    private int productId;

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @NotEmpty(message = "Brand name cannot be empty")
    private String brand;

    private float price;
}
