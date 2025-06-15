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

}
