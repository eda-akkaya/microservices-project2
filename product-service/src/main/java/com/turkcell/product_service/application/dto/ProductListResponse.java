package com.turkcell.product_service.application.dto;

import java.util.List;

public class ProductListResponse {
    private List<ProductResponse> products;
    private int totalCount;

    // Default constructor
    public ProductListResponse() {}

    // Constructor with parameters
    public ProductListResponse(List<ProductResponse> products, int totalCount) {
        this.products = products;
        this.totalCount = totalCount;
    }

    // Getters and Setters
    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}






