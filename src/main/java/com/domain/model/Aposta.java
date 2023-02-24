package com.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aposta {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToMany
    private List<Partida> partidas;

    @ElementCollection
    @MapKeyJoinColumn(name = "partidas_id")
    @Column(name = "opcao_resultado")
    @CollectionTable(name = "aposta_opcoes_resultados", joinColumns = @JoinColumn(name = "aposta_id"))
    private Map<Partida, OpcaoResultado> opcoesResultados = new HashMap<>();

    public int getNumeroAcertos() {
        int numeroAcertos = 0;
        for (Partida partida : partidas) {
            Resultado resultadoReal = partida.getResultado();
            OpcaoResultado opcaoAposta = opcoesResultados.get(partida);
            if (opcaoAposta != null && opcaoAposta.getResultado().equals(resultadoReal)) {
                numeroAcertos++;
            }
        }
        return numeroAcertos;
    }

}
