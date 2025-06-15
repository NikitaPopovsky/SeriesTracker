package com.github.NikitaPopovskiy.SeriesTracker.models;

import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "serials")
public class Serial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String original_name;
    private LocalDate first_air_date;
    private String overview;
    private String origin_country;
    private String poster_path;

    public Serial(String name, String original_name, LocalDate first_air_date, String overview, String origin_country, String poster_path) {
        this.name = name;
        this.original_name = original_name;
        this.first_air_date = first_air_date;
        this.overview = overview;
        this.origin_country = origin_country;
        this.poster_path = poster_path;
    }

    public Serial() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public LocalDate getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(LocalDate first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
