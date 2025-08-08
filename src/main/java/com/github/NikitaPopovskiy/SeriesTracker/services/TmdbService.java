package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.github.NikitaPopovskiy.SeriesTracker.*;
import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import okhttp3.*;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class TmdbService {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper mapper;
    private final String apiUrl;
    private final String apiKey;


    public TmdbService(OkHttpClient okHttpClient, ObjectMapper mapper,
                       @Value("${tmdb.api.base.url}") String apiUrl,
                       @Value("${tmdb.api.key}") String apiKey) {
        this.okHttpClient = okHttpClient;
        this.mapper = mapper;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public List<TmdbDto> searchSerials(String name) {

        Request request = RequestInitialization(name);
        List<TmdbDto> serials = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                String errorBode = response.body() != null ? response.body().string() : "No error body";
                System.err.println("HTTP Error: " + response.code() + " - " + response.message());
                System.err.println("Error details: " + errorBode);
                throw new IOException("HTTP error: " + response.code());
            }

            serials = parseSerials(response);
        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
            e.printStackTrace();

            if (e instanceof java.net.UnknownHostException) {
                System.err.println("Host not found!");
            } else if (e instanceof java.net.ConnectException) {
                System.err.println("Connection refused!");
            }
            serials = Collections.emptyList();
        }

        CashSerials.addAllCashSerials(serials);
        return serials;

    }

    private List<TmdbDto> parseSerials(Response response) {
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

    private Request RequestInitialization(String name) {
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
