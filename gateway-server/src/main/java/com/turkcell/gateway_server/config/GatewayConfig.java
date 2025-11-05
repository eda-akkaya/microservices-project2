package com.turkcell.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/*
 Bu sınıf, bir Spring Configuration Class’tır.
Yani Spring uygulaması başladığında bu sınıftaki @Bean metodu çalışır ve
 Gateway’e routeların (yönlendirme kurallarının) nasıl olacağını söyler.

Gateway’in görevi: dışarıdan gelen HTTP isteklerini, doğru mikroservise yönlendirmektir.
 */
@Configuration
public class GatewayConfig {
        /*
         * RouteLocator, Gateway’in route (yönlendirme) listesini temsil eder.
         * Yani:
         * “Hangi URL, hangi servise gider?”
         * “İstek başarısız olursa kaç kere denensin?” gibi ayarları içerir.
         * 
         * RouteLocatorBuilder, bu route’ları programatik olarak (Java koduyla)
         * tanımlamamızı sağlar.
         * Yani YAML dosyası yerine kodla yapıyoruz.
         */
        @Bean
        public RouteLocator routeLocator(RouteLocatorBuilder builder) {
                return builder
                                .routes()
                                .route("product-service", r -> r
                                                .path("/api/v1/products/**")
                                                .filters(f -> f
                                                                .retry(config -> config.setRetries(3)))
                                                .uri("lb://product-service"))

                                .route("fallback", r -> r
                                                .path("/**")
                                                .filters(f -> f.setStatus(HttpStatus.NOT_FOUND))
                                                .uri("no://op"))
                                .build();
        }
}
/*
 * Birinci Route: “product-service”
 * "product-service" Route’un ismi (loglarda bu isimle görünür).
 * .path("/api/v1/products/**") Bu path’e gelen tüm istekler bu route’a girer.
 * (örnek: /api/v1/products/1)
 * .filters(f -> f.retry(config -> config.setRetries(3))) Eğer istek başarısız
 * olursa (örneğin servis geçici olarak kapalıysa), Gateway otomatik olarak 3
 * kez yeniden dener.
 * .uri("lb://product-service") Bu istekleri Eureka’daki “product-service”
 * servisine yönlendir demektir. lb:// LoadBalancer kullanıldığını gösterir.
 * 
 * /api/v1/products/** yoluna gelen istekleri product-service mikroservisine
 * yönlendir.
 * Eğer servis geçici olarak yanıt vermiyorsa, 3 defa yeniden dene.
 * ------------------
 * İkinci Route: “fallback” (yedek route)
 * "fallback" Bu route’un ismi.
 * .path("/**") Diğer hiçbir route’a uymayan tüm yollar buraya düşer.
 * .filters(f -> f.setStatus(HttpStatus.NOT_FOUND)) Bu isteklere 404 Not Found
 * döndür.
 * .uri("no://op") Bu URI aslında sahte bir yönlendirmedir — yani gerçek bir
 * servise gitmez. (sadece “no operation”)
 * 
 * Bu route, default hata sayfası gibi çalışır.
 * Hiçbir route eşleşmezse, “404 NOT FOUND” döndürür.
 */