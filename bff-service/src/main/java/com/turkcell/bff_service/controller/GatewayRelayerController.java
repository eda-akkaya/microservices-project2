package com.turkcell.bff_service.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RequestMapping("/api")
@RestController
public class GatewayRelayerController {
    private final WebClient webClient;

    public GatewayRelayerController(WebClient webClient) {
        this.webClient = webClient;
    }

    @RequestMapping("/**") // gelen tüm istekleri tek bir yere yönlendirme
    // tüm istekler buraya gelecek; istek, buradan gateway'e yönlendirilecek. bu
    // fonksiyonun görevi bu
    public Mono<ResponseEntity<byte[]>> relay(ServerWebExchange exchange,
            @RequestBody(required = false) Mono<byte[]> body) {
        URI fullPath = exchange.getRequest().getURI();
        String downStreamPath = exchange.getRequest().getURI().getPath(); // api/v1/products
        String query = exchange.getRequest().getURI().getRawQuery(); // page=0
        // id=1&name=abc&c=a

        // localhost:8989/api/v1/products?page=0

        String pathWithQuery = query != null ? downStreamPath + "?" + query : downStreamPath;

        String fullRequestPath = "http://gateway-server/" + pathWithQuery;

        return webClient
                .method(exchange.getRequest().getMethod()) // gelen isteğin metodu
                .uri(fullRequestPath) // yukaıda oluşturduğumuz
                .body(body != null ? BodyInserters.fromPublisher(body, byte[].class) : BodyInserters.empty())// yukarıdan
                                                                                                             // body
                                                                                                             // geliyorsa
                                                                                                             // onu
                                                                                                             // gönder
                                                                                                             // gelmiyorsa
                                                                                                             // boş
                .exchangeToMono(response -> response.toEntity(byte[].class)); // gelen cevabı byte array'e çevir
    }
}