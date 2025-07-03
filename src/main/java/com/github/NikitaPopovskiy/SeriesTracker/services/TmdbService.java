package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.util.*;
import reactor.core.publisher.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TmdbService {
    private final WebClient tmdbWebClient;

    public Mono<List<TmdbDto>> searchSerials(String name) {
        String codingName = URLEncoder.encode(name, StandardCharsets.UTF_8).replace("+","%20");
        String uri = UriComponentsBuilder.fromPath("search/multi")
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
