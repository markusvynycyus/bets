package com.api.assembler;

import com.api.dto.PartidaDTO;
import com.domain.model.Partida;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartidaModelAssembler {

    @Autowired
    private ModelMapper modelMaper;

    public PartidaDTO toModel(Partida partida) {
        return modelMaper.map(partida, PartidaDTO.class);
    }

    public List<PartidaDTO> toCollectionModel(List<Partida> partidas) {
        return partidas.stream()
                .map(partida -> toModel(partida))
                .collect(Collectors.toList());
    }


}
