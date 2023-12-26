package com.onlineshop.consumer.catalogue.repository;

import com.onlineshop.consumer.catalogue.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Product, Integer> {
}
