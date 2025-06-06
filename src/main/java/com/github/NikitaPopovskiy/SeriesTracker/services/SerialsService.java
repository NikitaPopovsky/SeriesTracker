package com.github.NikitaPopovskiy.SeriesTracker.services;

import org.springframework.stereotype.*;

import java.util.*;
import com.github.NikitaPopovskiy.SeriesTracker.models.Serial;

@Service
public class SerialsService {
    public String addSerial (String title) {
        return "Сериал " + title + " успешно добавлен!";
    }

    public String deleteSerial (String title) {
        return "Сериал " + title + " успешно удален!";
    }

    public List<Serial> listSerials () {
        List <Serial> list = new ArrayList<>();
        Serial s1 = new Serial();
        s1.setName("Очень странные дела");
        s1.setId(1);
        list.add(s1);

        Serial s2 = new Serial();
        s2.setName("Игра в кальмара");
        s2.setId(2);
        list.add(s2);
        return list;
    }
}
