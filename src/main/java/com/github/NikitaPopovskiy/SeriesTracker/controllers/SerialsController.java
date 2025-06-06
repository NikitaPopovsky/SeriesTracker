package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @DeleteMapping ("/delete")
    public String deleteSerial (@RequestParam String title) {
        return serialsService.deleteSerial(title);
    }

    @GetMapping ("/list")
    public List<Serial> listSerials () {
        return serialsService.listSerials();
    }

}
