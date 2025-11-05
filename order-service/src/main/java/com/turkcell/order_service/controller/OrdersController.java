package com.turkcell.order_service.controller;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    // kafka ile iletişim kurmamızı sağlıyor
    private final StreamBridge streamBridge;

    public OrdersController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderDto dto) {
        OrderCreatedEvent event = new OrderCreatedEvent(dto.productId());
        // event best-practice: hangi event olursa olsun Message generic yapısıyla
        // sarmalla
        // mesajı sarmallıyoruz: kafka'nın sahip lması gereken özel bilgileri (eventId,
        // header) ekliyoruz.
        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(event).build();
        // kafka eventi fırlat

        try {
            boolean isSent = streamBridge.send("orderCreated-out-0", message);
            if (!isSent)
                System.out.println("Mesaj gönderilemedi.");
        } catch (Exception e) {
            System.out.println("mesaj gönderilemedi.");
        }

        return dto.productId;
    }

    // mimari açıdan doğru değil örnek olması için burada
    record CreateOrderDto(String productId) {
    }

    record OrderCreatedEvent(String productId) {
    }

}
