package com.turkcell.product_service.domain.model;

import com.turkcell.product_service.domain.valueobject.Money;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.domain.valueobject.Stock;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
    private ProductId id;
    private String name;
    private String description;
    private Money price;
    private Stock stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(ProductId id, String name, String description, Money price, Stock stock) {
        this.id = id;
        this.name = validateName(name);
        this.description = validateDescription(description);
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor for existing products (with timestamps)
    public Product(ProductId id, String name, String description, Money price, Stock stock, 
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = validateName(name);
        this.description = validateDescription(description);
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Product name must be at least 2 characters");
        }
        return name.trim();
    }

    private String validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Product description cannot be null or empty");
        }
        if (description.trim().length() < 10) {
            throw new IllegalArgumentException("Product description must be at least 10 characters");
        }
        return description.trim();
    }

    public void updateName(String name) {
        this.name = validateName(name);
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description = validateDescription(description);
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePrice(Money price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be null");
        }
        this.stock = stock;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}









