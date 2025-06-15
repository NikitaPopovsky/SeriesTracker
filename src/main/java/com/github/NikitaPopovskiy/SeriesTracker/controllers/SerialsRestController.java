package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestController
public class SerialsRestController {
    private final SerialsService serialsService;

    public SerialsRestController (SerialsService serialsService) {
        this.serialsService = serialsService;
    }

    @PostMapping ("/api/v1/add")
    public String addSerial (
            @RequestParam String name,
            @RequestParam String original_name,
            @RequestParam LocalDate first_air_date,
            @RequestParam String overview,
            @RequestParam String origin_country,
            @RequestParam String poster_path) {
        return serialsService.addSerial(name, original_name, first_air_date, overview, origin_country, poster_path);
    }

    @DeleteMapping ("/api/v1/delete")
    public String deleteSerial (@RequestParam Long id) {
        return serialsService.deleteSerial(id);
    }

    @GetMapping ("/api/v1/list")
    public Iterable<Serial> listSerials () {
        return serialsService.listSerials();
    }

}
