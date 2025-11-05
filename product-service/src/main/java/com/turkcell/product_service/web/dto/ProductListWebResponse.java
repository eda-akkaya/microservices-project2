package com.turkcell.product_service.web.dto;

import java.util.List;

public class ProductListWebResponse {
    private List<ProductWebResponse> products;
    private int totalCount;

    // Default constructor
    public ProductListWebResponse() {}

    // Constructor with parameters
    public ProductListWebResponse(List<ProductWebResponse> products, int totalCount) {
        this.products = products;
        this.totalCount = totalCount;
    }

    // Getters and Setters
    public List<ProductWebResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWebResponse> products) {
        this.products = products;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}







