package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.apache.catalina.connector.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.util.*;
import reactor.core.publisher.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@RequiredArgsConstructor
@Slf4j
public class TmdbService {
    private final WebClient tmdbWebClient;

    public Mono<List<TmdbDto>> searchSerials(String name) {


        //+отладка
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/multi?query=%D0%9E%D1%87%D0%B5%D0%BD%D1%8C%20%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B4%D0%B5%D0%BB%D0%B0&include_adult=false&language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "ТУТ БУДЕТ ТОКЕН")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                String errorBode = response.body() != null ? response.body().string() : "No error body";
                System.err.println("HTTP Error: " + response.code() + " - " + response.message());
                System.err.println("Error details: " + errorBode);
                throw new IOException("HTTP error: " + response.code());
            }

            String responseData = response.body().string();
            System.out.println("Success: " + responseData);

        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
            e.printStackTrace();

            if (e instanceof java.net.UnknownHostException) {
                System.err.println("Host not found!");
            } else if (e instanceof java.net.ConnectException) {
                System.err.println("Connection refused!");
            }
        }

        //-отладка

        String codingName = URLEncoder.encode(name, StandardCharsets.UTF_8).replace("+","%20");
        String uri = UriComponentsBuilder.fromPath("/search/multi")
                .queryParam("query", codingName)
                .queryParam("include_adult", false)
                .queryParam("language", "en-US")
                .queryParam("page",1)
                .build()
                .toUriString();

        return tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("TMDB Error" + errorBody))))
                .bodyToMono(new ParameterizedTypeReference<Map<String,List<TmdbDto>>>() {})
                .map(response -> {
                    Object results = response.get("results");
                    if (results instanceof List) {
                        return (List<TmdbDto>) results;
                    } else {
                        log.warn ("Unexpected 'results' type :" + results);
                        return Collections.emptyList();
                    }

                });
    }

}
