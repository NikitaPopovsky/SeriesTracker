package com.github.NikitaPopovskiy.SeriesTracker.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Data
public class TmdbDto {
    @JsonProperty ("id")
    private int idTmdb;
    private String name;
    @JsonProperty ("original_name")
    private String originalName;
    @JsonProperty ("first_air_date")
    private LocalDate firstAirDate;
    private String overview;
    @JsonProperty ("origin_country")
    private String originCountry;
    @JsonProperty ("poster_path")
    private String posterPath;

}
