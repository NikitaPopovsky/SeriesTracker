package com.github.NikitaPopovskiy.SeriesTracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@Entity
@Table (name = "lastseasones")
public class LastSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_serial_tmdb")
    private int idSerialTmdb;
    @Column(name = "season_number")
    private byte seasonNumber;
    @Column(name = "episode_count")
    private byte episodeCount;
    @Column(name = "air_date")
    private LocalDate airDate;
}



