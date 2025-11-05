package com.turkcell.product_service.web.mapper;

import com.turkcell.product_service.application.dto.CreateProductRequest;
import com.turkcell.product_service.application.dto.ProductListResponse;
import com.turkcell.product_service.application.dto.ProductResponse;
import com.turkcell.product_service.application.dto.UpdateProductRequest;
import com.turkcell.product_service.web.dto.CreateProductWebRequest;
import com.turkcell.product_service.web.dto.ProductListWebResponse;
import com.turkcell.product_service.web.dto.ProductWebResponse;
import com.turkcell.product_service.web.dto.UpdateProductWebRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ProductWebMapper {
    
    public static CreateProductRequest toApplicationRequest(CreateProductWebRequest webRequest) {
        if (webRequest == null) {
            return null;
        }
        
        return new CreateProductRequest(
            webRequest.getName(),
            webRequest.getDescription(),
            webRequest.getPriceAmount(),
            webRequest.getPriceCurrency(),
            webRequest.getStockQuantity()
        );
    }
    
    public static UpdateProductRequest toApplicationRequest(UpdateProductWebRequest webRequest) {
        if (webRequest == null) {
            return null;
        }
        
        return new UpdateProductRequest(
            webRequest.getName(),
            webRequest.getDescription()
        );
    }
    
    public static ProductWebResponse toWebResponse(ProductResponse applicationResponse) {
        if (applicationResponse == null) {
            return null;
        }
        
        return new ProductWebResponse(
            applicationResponse.getId(),
            applicationResponse.getName(),
            applicationResponse.getDescription(),
            applicationResponse.getPriceAmount(),
            applicationResponse.getPriceCurrency(),
            applicationResponse.getStockQuantity(),
            applicationResponse.getCreatedAt(),
            applicationResponse.getUpdatedAt()
        );
    }
    
    public static ProductListWebResponse toWebResponse(ProductListResponse applicationResponse) {
        if (applicationResponse == null) {
            return new ProductListWebResponse(List.of(), 0);
        }
        
        List<ProductWebResponse> webResponses = applicationResponse.getProducts().stream()
            .map(ProductWebMapper::toWebResponse)
            .collect(Collectors.toList());
            
        return new ProductListWebResponse(webResponses, applicationResponse.getTotalCount());
    }
}








