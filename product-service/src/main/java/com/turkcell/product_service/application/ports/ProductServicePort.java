package com.turkcell.product_service.application.ports;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;

public interface ProductServicePort {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(String id);
    ProductListResponse getAllProducts();
    ProductResponse updateProduct(String id, UpdateProductRequest request);
    void deleteProduct(String id);
}






