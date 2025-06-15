package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.repositories.*;
import org.springframework.stereotype.*;

import com.github.NikitaPopovskiy.SeriesTracker.models.Serial;

import java.time.*;

@Service
public class SerialsService {
    private final SerialsRepository serialsRepository;

    public SerialsService (SerialsRepository serialsRepository) {
        this.serialsRepository = serialsRepository;
    }

    public String addSerial (String serialName, String original_name, LocalDate first_air_date, String overview,
                             String origin_country, String poster_path) {
        Serial newSerial = new Serial(serialName, original_name, first_air_date, overview, origin_country, poster_path);
        serialsRepository.save(newSerial);
        return "Сериал " + serialName + " успешно добавлен!";
    }

    public String deleteSerial (Long id) {
        serialsRepository.deleteById(id);
        return "Сериал " + id + " успешно удален!";
    }

    public Iterable<Serial> listSerials () {
        return serialsRepository.findAll();
    }
}
