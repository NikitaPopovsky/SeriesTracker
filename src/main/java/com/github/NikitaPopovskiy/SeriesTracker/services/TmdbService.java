package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

import okhttp3.*;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class TmdbService {
    private final OkHttpClient okHttpClient;
    private final String apiUrl;
    private final String apiKey;

    public TmdbService(
            @Value("${tmdb.api.base.url}") String apiUrl,
            @Value("${tmdb.api.key}") String apiKey) {
        this.okHttpClient = new OkHttpClient();
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public List<TmdbDto> searchSerials(String name) {

        Request request = RequestInitialization(name, apiUrl, apiKey);

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                String errorBode = response.body() != null ? response.body().string() : "No error body";
                System.err.println("HTTP Error: " + response.code() + " - " + response.message());
                System.err.println("Error details: " + errorBode);
                throw new IOException("HTTP error: " + response.code());
            }

            return parseSerials(response);
        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
            e.printStackTrace();

            if (e instanceof java.net.UnknownHostException) {
                System.err.println("Host not found!");
            } else if (e instanceof java.net.ConnectException) {
                System.err.println("Connection refused!");
            }
            return Collections.emptyList();
        }

    }

    private List<TmdbDto> parseSerials(Response response) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            String json = response.body().string();
            JsonNode rootNode = mapper.readTree(json);
            JsonNode resultNode = rootNode.get("results");

            if (resultNode == null || !resultNode.isArray()) {

                return Collections.emptyList();
            }

            return Arrays.asList(mapper.treeToValue(resultNode, TmdbDto[].class));

        } catch (IOException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());

            return Collections.emptyList();
        }

    }

    private Request RequestInitialization(String name, String apiUrl, String apiKey) {
        HttpUrl url = HttpUrl.parse(apiUrl)
                .newBuilder()
                .addPathSegments("search")
                .addPathSegments("multi")
                .addQueryParameter("query", name)
                .addQueryParameter("include_adult", "false")
                .addQueryParameter("language", "ru-RU")
                .addQueryParameter("page","1")
                .build();

        String urlString = url.toString();

        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
        return request;
    }

}
