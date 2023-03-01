package com.api.assembler;


import com.api.dto.ApostaDTO;
import com.domain.model.Aposta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApostaModelAssembler {

    @Autowired
    private ModelMapper modelMaper;

    public ApostaDTO toModel(Aposta aposta) {
        return modelMaper.map(aposta, ApostaDTO.class);
    }

    public List<ApostaDTO> toCollectionModel(List<Aposta> apostas) {
        return apostas.stream()
                .map(aposta -> toModel(aposta))
                .collect(Collectors.toList());
    }
}
