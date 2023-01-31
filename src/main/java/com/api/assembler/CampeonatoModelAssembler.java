package com.api.assembler;

import com.api.dto.CampeonatoDTO;
import com.api.dto.TimeDTO;
import com.domain.model.Campeonato;
import com.domain.model.Time;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampeonatoModelAssembler {

    @Autowired
    private ModelMapper modelMaper;

    public CampeonatoDTO toModel(Campeonato campeonato) {

        return modelMaper.map(campeonato, CampeonatoDTO.class);
    }

    public List<CampeonatoDTO> toCollectionModel(List<Campeonato> campeonatos) {
        return campeonatos.stream()
                .map(campeonato -> toModel(campeonato))
                .collect(Collectors.toList());
    }
}
