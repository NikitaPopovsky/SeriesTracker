package com.github.NikitaPopovskiy.SeriesTracker.controllers;

import com.github.NikitaPopovskiy.SeriesTracker.models.*;
import com.github.NikitaPopovskiy.SeriesTracker.services.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SerialsFrontController {
    private final SerialsService serialsService;

    public SerialsFrontController(SerialsService serialsService) {
        this.serialsService = serialsService;
    }

    @GetMapping("/")
    public String home(Model model) {
        Iterable<Serial> serialList = serialsService.listSerials();
        model.addAttribute("serialList", serialList);
        return "serials-list";
    }

    @GetMapping ("/search")
    public String searchForm () {
        return "search-serial";
    }

}
