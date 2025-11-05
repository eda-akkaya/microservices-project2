package com.turkcell.product_service.web.dto;

import jakarta.validation.constraints.Size;

public class UpdateProductWebRequest {
    
    @Size(min = 2, message = "Product name must be at least 2 characters")
    private String name;
    
    @Size(min = 10, message = "Product description must be at least 10 characters")
    private String description;

    // Default constructor
    public UpdateProductWebRequest() {}

    // Constructor with parameters
    public UpdateProductWebRequest(String name, String description) {
        this.name = name;
        this.description = description;
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
}









