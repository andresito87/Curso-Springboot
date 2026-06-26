package com.learning.mokito.mokito_demo.service;

import com.learning.mokito.mokito_demo.model.Product;
import com.learning.mokito.mokito_demo.repository.IProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        productRepository.save(product);
    }
}
