package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
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

    @PostMapping ("/api/v1/add")
    public void addSerial (@RequestParam int idTmdb) {
        serialsService.addSerial(idTmdb);
    }


}
