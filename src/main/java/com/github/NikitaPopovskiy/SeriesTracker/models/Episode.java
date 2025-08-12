package com.github.NikitaPopovskiy.SeriesTracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@Entity
@Table (name = "Episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_serial_tmdb")
    private int idSerialTmdb;
    @Column(name = "season_number")
    private byte seasonNumber;
    @Column(name = "episode_number")
    private byte episodeNumber;
    private String name;
    @Column(name = "air_date")
    private LocalDate airDate;
}
