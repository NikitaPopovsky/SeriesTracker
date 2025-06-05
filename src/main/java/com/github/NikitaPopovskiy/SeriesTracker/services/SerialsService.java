package com.github.NikitaPopovskiy.SeriesTracker.services;

import org.springframework.stereotype.*;

@Service
public class SerialsService {
    public String addSerial (String title) {
        return "Сериал " + title + "успешно добавлен!";
    }
}
