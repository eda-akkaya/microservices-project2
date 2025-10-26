package com.turkcell.order_service.controller;

@RestController
@RequestMapping()
public class OrdersController {
    @PostMapping()
    public String postMethodName(@RequestBody CreateOrderDto dto) {
        return dto.productId;
    }

    record CreateOrderDto(String productId) {
    }

}
