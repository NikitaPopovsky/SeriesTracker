package com.github.NikitaPopovskiy.SeriesTracker;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CashSerials {
    private static Map<Integer, TmdbDto> cashMap = new ConcurrentHashMap<>();

    public static void addAllCashSerials(List<TmdbDto> serials) {
        cashMap = serials.stream()
                .collect(Collectors.toConcurrentMap(TmdbDto::getIdTmdb, serial -> serial));
    }

    public static TmdbDto getCashSerial(int idTmdb) {
        return cashMap.get(idTmdb);
    }
}
