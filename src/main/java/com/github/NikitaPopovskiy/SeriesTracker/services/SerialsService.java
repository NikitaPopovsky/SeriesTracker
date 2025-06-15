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

    public String addSerial (int idTmdb, String serialName, String originalName, LocalDate firstAirDate,
                             String overview, String originCountry, String posterPath) {
        Serial newSerial = new Serial(idTmdb, serialName, originalName, firstAirDate, overview,
                originCountry, posterPath);
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
