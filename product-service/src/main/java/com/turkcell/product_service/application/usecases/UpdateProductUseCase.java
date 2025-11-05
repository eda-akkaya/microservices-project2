package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.application.mapper.ProductApplicationMapper;
import com.turkcell.product_service.domain.exception.ProductNotFoundException;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.ProductId;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {
    
    private final ProductRepository productRepository;
    
    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public ProductResponse execute(String id, UpdateProductRequest request) {
        ProductId productId = new ProductId(id);
        
        return productRepository.findById(productId)
            .map(product -> {
                // Update fields if provided
                if (request.getName() != null && !request.getName().trim().isEmpty()) {
                    product.updateName(request.getName());
                }
                if (request.getDescription() != null && !request.getDescription().trim().isEmpty()) {
                    product.updateDescription(request.getDescription());
                }
                
                // Save updated product
                Product updatedProduct = productRepository.save(product);
                return ProductApplicationMapper.toResponse(updatedProduct);
            })
            .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
}
