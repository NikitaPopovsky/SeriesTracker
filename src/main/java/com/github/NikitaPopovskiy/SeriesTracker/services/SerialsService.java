package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.*;
import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import com.github.NikitaPopovskiy.SeriesTracker.repositories.*;
import org.springframework.stereotype.*;

import com.github.NikitaPopovskiy.SeriesTracker.models.Serial;

import java.time.*;
import java.util.*;

@Service
public class SerialsService {
    private final SerialsRepository serialsRepository;

    public SerialsService (SerialsRepository serialsRepository) {
        this.serialsRepository = serialsRepository;
    }

    public void addSerial (int idTmdb) {
        TmdbDto dto = CashSerials.getCashSerial(idTmdb);

        List<Serial> serials = serialsRepository.findSerialByIdTmdb(idTmdb);

        if (serials.isEmpty()) {
            Serial newSerial = new Serial(dto);
            serialsRepository.save(newSerial);
        }

    }

    public String deleteSerial (Long id) {
        serialsRepository.deleteById(id);
        return "Сериал " + id + " успешно удален!";
    }

    public Iterable<Serial> listSerials () {
        return serialsRepository.findAll();
    }
}
