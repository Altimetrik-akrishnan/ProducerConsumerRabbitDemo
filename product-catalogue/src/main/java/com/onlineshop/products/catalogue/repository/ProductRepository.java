package com.onlineshop.products.catalogue.repository;

import com.onlineshop.products.catalogue.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, Integer> {

    Optional<Product> findByProductId(int id);

    /*List<Product> findByCategoryAndPrice_AmountBetween(String category, float min, float max);

    List<Product> findByCategoryAndInventory_AvailableGreaterThan(String category, short value, Sort by);

    boolean existsByCategory(String category);

    void deleteByNameAndBrandAndCategory(String name, String brand, String category);*/
}
