package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.mapper.ProductApplicationMapper;
import com.turkcell.product_service.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductsUseCase {
    
    private final ProductRepository productRepository;
    
    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public ProductListResponse execute() {
        return ProductApplicationMapper.toListResponse(productRepository.findAll());
    }
}






