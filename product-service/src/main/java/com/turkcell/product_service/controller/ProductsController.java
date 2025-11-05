package com.turkcell.product_service.controller;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.ports.ProductServicePort;
import com.turkcell.product_service.web.dto.CreateProductWebRequest;
import com.turkcell.product_service.web.dto.ProductListWebResponse;
import com.turkcell.product_service.web.dto.ProductWebResponse;
import com.turkcell.product_service.web.dto.UpdateProductWebRequest;
import com.turkcell.product_service.web.mapper.ProductWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {
    
    private final ProductServicePort productService;
    
    public ProductsController(ProductServicePort productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public ResponseEntity<ProductListWebResponse> getAllProducts() {
        ProductListResponse response = productService.getAllProducts();
        ProductListWebResponse webResponse = ProductWebMapper.toWebResponse(response);
        return ResponseEntity.ok(webResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductWebResponse> getProductById(@PathVariable String id) {
        ProductResponse response = productService.getProductById(id);
        ProductWebResponse webResponse = ProductWebMapper.toWebResponse(response);
        return ResponseEntity.ok(webResponse);
    }

    @PostMapping
    public ResponseEntity<ProductWebResponse> createProduct(@Valid @RequestBody CreateProductWebRequest request) {
        CreateProductRequest applicationRequest = ProductWebMapper.toApplicationRequest(request);
        ProductResponse response = productService.createProduct(applicationRequest);
        ProductWebResponse webResponse = ProductWebMapper.toWebResponse(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductWebResponse> updateProduct(@PathVariable String id, 
                                                          @Valid @RequestBody UpdateProductWebRequest request) {
        UpdateProductRequest applicationRequest = ProductWebMapper.toApplicationRequest(request);
        ProductResponse response = productService.updateProduct(id, applicationRequest);
        ProductWebResponse webResponse = ProductWebMapper.toWebResponse(response);
        return ResponseEntity.ok(webResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}