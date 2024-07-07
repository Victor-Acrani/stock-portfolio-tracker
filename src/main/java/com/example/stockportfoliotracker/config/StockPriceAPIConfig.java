package com.example.stockportfoliotracker.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Getter
@Configuration
public class StockPriceAPIConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://financialmodelingprep.com/api/v3/quote")
                .build();
    }

    private @Value("${stock.api.key}") String apiKey;

}