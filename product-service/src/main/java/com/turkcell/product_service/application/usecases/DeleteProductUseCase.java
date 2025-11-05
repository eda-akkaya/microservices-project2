package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.domain.exception.ProductNotFoundException;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.ProductId;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {
    
    private final ProductRepository productRepository;
    
    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public void execute(String id) {
        ProductId productId = new ProductId(id);
        
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        
        productRepository.deleteById(productId);
    }
}






