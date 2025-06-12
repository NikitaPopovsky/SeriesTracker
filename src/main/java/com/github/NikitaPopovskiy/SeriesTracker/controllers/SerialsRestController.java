package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class SerialsRestController {
    private final SerialsService serialsService;

    public SerialsRestController (SerialsService serialsService) {
        this.serialsService = serialsService;
    }

    @PostMapping ("/api/v1/add")
    public String addSerial (@RequestParam String title) {
        return serialsService.addSerial(title);
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
