package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import lombok.*;
import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;

import java.net.*;
import java.nio.charset.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TmdbService {
    private final WebClient tmdbWebClient;

    public List<TmdbDto> searchSerials(String name) {
        String uri = "search/multi?query=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        return tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String,List<TmdbDto>>>() {})
                .map(response -> response.get("results"))
                .block();
    }

}
