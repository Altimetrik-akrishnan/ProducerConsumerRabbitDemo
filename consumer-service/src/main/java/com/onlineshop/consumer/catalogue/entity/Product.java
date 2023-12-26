package com.onlineshop.consumer.catalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products_consumer")
public class Product {

    @NotNull
    private int productId;

    @NotNull
    private String name;

    @NotNull
    private String brand;

    private float price;
}
