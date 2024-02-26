package com.microservices.gateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private AuthFilter authFilter;

    public GatewayConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://user-service"))
                .route("authentication-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://authentication-service"))
                .route("shop-service", r -> r.path("/orders/**", "/shop/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://shop-service"))
                .route("storage-service", r -> r.path("/storage/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://storage-service"))

                .build();
    }


}
