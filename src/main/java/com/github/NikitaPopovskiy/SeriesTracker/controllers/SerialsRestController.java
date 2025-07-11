package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.time.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class SerialsRestController {
    private final SerialsService serialsService;
    private final TmdbService tmdbService;

    @PostMapping ("/api/v1/add")
    public String addSerial (
            @RequestParam int idTmdb,
            @RequestParam String name,
            @RequestParam String originalName,
            @RequestParam LocalDate firstAirDate,
            @RequestParam String overview,
            @RequestParam String originCountry,
            @RequestParam String posterPath) {
        return serialsService.addSerial(idTmdb, name, originalName, firstAirDate, overview, originCountry, posterPath);
    }

    @DeleteMapping ("/api/v1/delete")
    public String deleteSerial (@RequestParam Long id) {
        return serialsService.deleteSerial(id);
    }

    @GetMapping ("/api/v1/list")
    public Iterable<Serial> listSerials () {
        return serialsService.listSerials();
    }

    @GetMapping ("/api/v1/search")
    public List<TmdbDto> searchSerials (@RequestParam String query) {
        List<TmdbDto> serials = tmdbService.searchSerials(query);
        return serials;
    }


}
