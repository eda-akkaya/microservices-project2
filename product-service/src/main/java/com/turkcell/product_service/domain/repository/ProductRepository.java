package com.turkcell.product_service.domain.repository;

import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.valueobject.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(ProductId id);
    List<Product> findAll();
    void deleteById(ProductId id);
    boolean existsById(ProductId id);
}









