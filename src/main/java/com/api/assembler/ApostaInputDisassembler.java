package com.api.assembler;

import com.api.dto.input.ApostaInput;
import com.api.dto.input.PartidaInput;
import com.domain.model.Aposta;
import com.domain.model.Partida;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApostaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Aposta toDomainObject(ApostaInput apostaInput) {

        return modelMapper.map(apostaInput, Aposta.class);
    }

    public void copyToDomainObject(ApostaInput apostaInput, Aposta aposta) {
        modelMapper.map(apostaInput, aposta);
    }
}
