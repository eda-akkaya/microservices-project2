package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductApplicationMapper;
import com.turkcell.product_service.domain.exception.ProductNotFoundException;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.ProductId;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUseCase {
    
    private final ProductRepository productRepository;
    
    public GetProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public ProductResponse execute(String id) {
        ProductId productId = new ProductId(id);
        
        return productRepository.findById(productId)
            .map(ProductApplicationMapper::toResponse)
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
}






