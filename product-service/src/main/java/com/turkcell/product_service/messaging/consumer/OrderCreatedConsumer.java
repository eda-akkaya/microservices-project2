package com.turkcell.product_service.messaging.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCreatedConsumer {
    @Bean
    public Consumer<OrderCreatedEvent> orderCreated() {
        // eventi parametre olarak almadık,ne zaman geleceği belli değil,
        // fonksiyon referansı veriyoruz, event geldiği zaman fonksiyon çalışacak
        return event -> {
            System.out.println("yeni bir order oluşturuldu");
            System.out.println(event.productId());
        };
    }

    record OrderCreatedEvent(String productId) {
    }
}
