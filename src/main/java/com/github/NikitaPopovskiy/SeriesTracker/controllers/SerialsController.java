package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class SerialsController {
    private final SerialsService serialsService;

    public SerialsController (SerialsService serialsService) {
        this.serialsService = serialsService;
    }

    @PostMapping ("/add")
    public String addSerial (@RequestParam String title) {
        return serialsService.addSerial(title);
    }

}
