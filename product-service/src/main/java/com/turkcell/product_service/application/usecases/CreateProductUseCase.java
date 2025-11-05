package com.turkcell.product_service.application.usecases;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.mapper.ProductApplicationMapper;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.repository.ProductRepository;
import com.turkcell.product_service.domain.valueobject.Currency;
import com.turkcell.product_service.domain.valueobject.Money;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.domain.valueobject.Stock;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateProductUseCase {
    
    private final ProductRepository productRepository;
    
    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public ProductResponse execute(CreateProductRequest request) {
        // Generate unique ID
        String productId = UUID.randomUUID().toString();
        
        // Create domain objects
        ProductId id = new ProductId(productId);
        Money price = new Money(request.getPriceAmount(), Currency.valueOf(request.getPriceCurrency()));
        Stock stock = new Stock(request.getStockQuantity());
        
        // Create product domain entity
        Product product = new Product(id, request.getName(), request.getDescription(), price, stock);
        
        // Save product
        Product savedProduct = productRepository.save(product);
        
        // Convert to response DTO
        return ProductApplicationMapper.toResponse(savedProduct);
    }
}
