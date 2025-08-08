package com.github.NikitaPopovskiy.SeriesTracker.config;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import okhttp3.*;
import org.springframework.context.annotation.*;

import java.util.concurrent.*;

@Configuration
public class ConfigTmdb {

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

}
