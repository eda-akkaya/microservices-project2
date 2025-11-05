package com.turkcell.order_service.controller;

import java.util.UUID;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.order_service.messaging.outbox.OutboxMessage;
import com.turkcell.order_service.messaging.outbox.OutboxRepository;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    // kafka ile iletişim kurmamızı sağlıyor
    // private final StreamBridge streamBridge;

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public OrdersController(OutboxRepository outboxRepository,
            ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderDto dto) throws JsonProcessingException {
        OrderCreatedEvent event = new OrderCreatedEvent(dto.productId());
        // event best-practice: hangi event olursa olsun Message generic yapısıyla
        // sarmalla
        // mesajı sarmallıyoruz: kafka'nın sahip lması gereken özel bilgileri (eventId,
        // header) ekliyoruz.
        // Message<OrderCreatedEvent> message =
        // MessageBuilder.withPayload(event).build();
        // kafka eventi fırlat

        // aggregate: hangi domain nesnesiyle ilgili bir mesaj
        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setAggregateId(UUID.randomUUID()); // normalde db'de oluşan order'ın id'si
        outboxMessage.setAggregateType("Order");
        outboxMessage.setEventId(UUID.randomUUID());
        outboxMessage.setEventType("OrderCreatedEvent");
        // gönderilen mesajın/event'in serialize edilmiş hali.event nesnesini json
        // olarak yazmamız lazım

        outboxMessage.setPayloadJson(objectMapper.writeValueAsString(event));
        outboxRepository.save(outboxMessage);

        /*
         * try {
         * boolean isSent = streamBridge.send("orderCreated-out-0", message);
         * if (!isSent)
         * System.out.println("Mesaj gönderilemedi.");
         * } catch (Exception e) {
         * System.out.println("mesaj gönderilemedi.");
         * }
         * relayer'a taşındı
         */
        return dto.productId;
    }

    // mimari açıdan doğru değil örnek olması için burada
    record CreateOrderDto(String productId) {
    }

    public record OrderCreatedEvent(String productId) {
    }

}
