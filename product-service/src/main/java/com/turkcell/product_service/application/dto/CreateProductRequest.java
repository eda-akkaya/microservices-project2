package com.turkcell.product_service.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductRequest {
    
    @NotBlank(message = "Product name is required")
    @Size(min = 2, message = "Product name must be at least 2 characters")
    private String name;
    
    @NotBlank(message = "Product description is required")
    @Size(min = 10, message = "Product description must be at least 10 characters")
    private String description;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal priceAmount;
    
    @NotBlank(message = "Currency is required")
    private String priceCurrency;
    
    @NotNull(message = "Stock quantity is required")
    private Integer stockQuantity;

    // Default constructor
    public CreateProductRequest() {}

    // Constructor with parameters
    public CreateProductRequest(String name, String description, BigDecimal priceAmount, 
                               String priceCurrency, Integer stockQuantity) {
        this.name = name;
        this.description = description;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}






