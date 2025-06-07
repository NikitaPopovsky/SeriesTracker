package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.repositories.*;
import org.springframework.stereotype.*;

import com.github.NikitaPopovskiy.SeriesTracker.models.Serial;

@Service
public class SerialsService {
    private final SerialsRepository serialsRepository;

    public SerialsService (SerialsRepository serialsRepository) {
        this.serialsRepository = serialsRepository;
    }

    public String addSerial (String serialName) {
        Serial newSerial = new Serial();
        newSerial.setName(serialName);
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
