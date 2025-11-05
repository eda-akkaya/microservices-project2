package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.valueobject.Currency;
import com.turkcell.product_service.domain.valueobject.Money;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.domain.valueobject.Stock;

import java.util.List;
import java.util.stream.Collectors;

public class ProductApplicationMapper {
    
    public static ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }
        
        return new ProductResponse(
            product.getId().getValue(),
            product.getName(),
            product.getDescription(),
            product.getPrice().getAmount(),
            product.getPrice().getCurrency().name(),
            product.getStock().getQuantity(),
            product.getCreatedAt(),
            product.getUpdatedAt()
        );
    }
    
    public static ProductListResponse toListResponse(List<Product> products) {
        if (products == null) {
            return new ProductListResponse(List.of(), 0);
        }
        
        List<ProductResponse> productResponses = products.stream()
            .map(ProductApplicationMapper::toResponse)
            .collect(Collectors.toList());
            
        return new ProductListResponse(productResponses, productResponses.size());
    }
    
    public static Product toDomain(CreateProductRequest request, ProductId id) {
        if (request == null || id == null) {
            return null;
        }
        
        Money price = new Money(request.getPriceAmount(), Currency.valueOf(request.getPriceCurrency()));
        Stock stock = new Stock(request.getStockQuantity());
        
        return new Product(id, request.getName(), request.getDescription(), price, stock);
    }
}
