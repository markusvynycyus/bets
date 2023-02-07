package com.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Partida {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Campeonato campeonato;

    @ManyToOne
    private Time timeCasa;

    @ManyToOne
    private Time timeFora;
    private LocalDateTime dataPratida = LocalDateTime.now();

    private Long golsTimeCasa;

    private Long golsTimeFora;
}
