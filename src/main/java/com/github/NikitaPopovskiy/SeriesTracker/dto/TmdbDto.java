package com.github.NikitaPopovskiy.SeriesTracker.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
public class TmdbDto {
    @JsonProperty ("id")
    private int idTmdb;
    private String name;
    @JsonProperty ("original_name")
    private String originalName;
    @JsonProperty ("first_air_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate firstAirDate;
    private String overview;
    @JsonProperty ("origin_country")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> originCountry;
    @JsonProperty ("poster_path")
    private String posterPath;

}
