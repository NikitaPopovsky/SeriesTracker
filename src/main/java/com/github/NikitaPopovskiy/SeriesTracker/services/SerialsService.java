package com.github.NikitaPopovskiy.SeriesTracker.services;

import com.github.NikitaPopovskiy.SeriesTracker.cash.*;
import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import com.github.NikitaPopovskiy.SeriesTracker.repositories.*;
import org.springframework.stereotype.*;

import com.github.NikitaPopovskiy.SeriesTracker.models.Serial;

import java.util.*;

@Service
public class SerialsService {
    private final SerialsRepository serialsRepository;

    public SerialsService (SerialsRepository serialsRepository) {
        this.serialsRepository = serialsRepository;
    }

    public void addSerial (int idSerialTmdb) {
        TmdbDto dto = CashSerials.getCashSerial(idSerialTmdb);

        List<Serial> serials = serialsRepository.findSerialByIdSerialTmdb(idSerialTmdb);

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
