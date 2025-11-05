package com.turkcell.product_service.infrastructure.mapper;

import com.turkcell.product_service.domain.model.Product;
import com.turkcell.product_service.domain.valueobject.Currency;
import com.turkcell.product_service.domain.valueobject.Money;
import com.turkcell.product_service.domain.valueobject.ProductId;
import com.turkcell.product_service.domain.valueobject.Stock;
import com.turkcell.product_service.infrastructure.entity.ProductEntity;

public class ProductMapper {
    
    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        
        return new ProductEntity(
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
    
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Product(
            new ProductId(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            new Money(entity.getPriceAmount(), Currency.valueOf(entity.getPriceCurrency())),
            new Stock(entity.getStockQuantity()),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}






