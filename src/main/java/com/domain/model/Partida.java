package com.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private LocalDateTime dataPartida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Resultado resultado;

    public Resultado getResultado() {
        return resultado;
    }

}
