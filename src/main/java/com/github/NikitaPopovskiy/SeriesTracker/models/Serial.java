package com.github.NikitaPopovskiy.SeriesTracker.models;

import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "serials")
public class Serial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_tmdb")
    private int idTmdb;
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

    public Serial(int idTmdb, String name, String originalName, LocalDate firstAirDate, String overview,
                  String originCountry, String posterPath) {
        this.name = name;
        this.originalName = originalName;
        this.firstAirDate = firstAirDate;
        this.overview = overview;
        this.originCountry = originCountry;
        this.posterPath = posterPath;
        this.idTmdb = idTmdb;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public LocalDate getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(LocalDate firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getIdTmdb() {
        return idTmdb;
    }

    public void setIdTmdb(int idTmdb) {
        this.idTmdb = idTmdb;
    }
}
