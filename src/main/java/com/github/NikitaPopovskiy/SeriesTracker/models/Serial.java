package com.github.NikitaPopovskiy.SeriesTracker.models;

import com.github.NikitaPopovskiy.SeriesTracker.dto.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@Entity
@Table(name = "serials")
public class Serial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_serial_tmdb")
    private int idSerialTmdb;
    private String name;
    @Column(name = "original_name")
    private String originalName;
    @Column(name = "first_air_date")
    private LocalDate firstAirDate;
    private String overview;
    @Column(name = "origin_country")
    private String originCountry;
    @Column(name = "poster_path")
    private String posterPath;

    public Serial(TmdbDto tmdbDto) {
        this.idSerialTmdb = tmdbDto.getIdSerialTmdb();
        this.name = tmdbDto.getName();
        this.originalName = tmdbDto.getOriginalName();
        this.firstAirDate = tmdbDto.getFirstAirDate();
        this.overview = tmdbDto.getOverview();
        this.originCountry = tmdbDto.getOriginCountry().getFirst();
        this.posterPath = tmdbDto.getPosterPath();
    }

    public Serial() {

    }
}

