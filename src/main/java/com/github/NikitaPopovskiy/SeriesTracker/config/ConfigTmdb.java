package com.github.NikitaPopovskiy.SeriesTracker.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import okhttp3.*;
import org.apache.catalina.mapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import java.util.concurrent.*;

@Configuration
public class ConfigTmdb {
    @Value("${tmdb.api.base.url}")
    private String apiUrl;
    @Value("${tmdb.api.key}")
    private String apiKey;



    @Bean
    public OkHttpClient okHttpClient () {
        return new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    public ObjectMapper mapper () {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    public Request.Builder requestBuilder () {
        return new Request.Builder()
                .url(apiUrl)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey);

    }

}
