package com.learning.mokito.mokito_demo.repository;

import com.learning.mokito.mokito_demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    Optional<Product> findById(Long id);

    List<Product> findAll();

    void save(Product product);
}
