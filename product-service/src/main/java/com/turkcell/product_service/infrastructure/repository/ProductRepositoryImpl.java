package com.turkcell.product_service.infrastructure.repository;

import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.ProductId;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    
    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.put(product.getId().getValue(), product);
        return product;
    }
    
    @Override
    public Optional<Product> findById(ProductId id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(products.get(id.getValue()));
    }
    
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
    
    @Override
    public void deleteById(ProductId id) {
        if (id != null) {
            products.remove(id.getValue());
        }
    }
    
    @Override
    public boolean existsById(ProductId id) {
        if (id == null) {
            return false;
        }
        return products.containsKey(id.getValue());
    }
}
