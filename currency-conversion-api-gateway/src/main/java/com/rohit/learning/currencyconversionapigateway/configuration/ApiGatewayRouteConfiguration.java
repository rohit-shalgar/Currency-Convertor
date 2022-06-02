package com.rohit.learning.currencyconversionapigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class ApiGatewayRouteConfiguration {

    @Bean
    public RouteLocator gatewayApiRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p-> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .build();

    }
}
