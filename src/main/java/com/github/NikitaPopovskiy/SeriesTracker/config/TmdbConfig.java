package com.github.NikitaPopovskiy.SeriesTracker.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.reactive.function.client.*;

@Configuration
public class TmdbConfig {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiUrl;

    @Bean
    public WebClient tmdbWebClient() {
        return WebClient.builder()
                .baseUrl(tmdbApiUrl)
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", "Bearer " + tmdbApiKey)
                .build();
    }

}
