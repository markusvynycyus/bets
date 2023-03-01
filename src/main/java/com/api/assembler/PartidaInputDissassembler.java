package com.api.assembler;

import com.api.dto.input.PartidaInput;
import com.domain.model.Partida;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartidaInputDissassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Partida toDomainObject(PartidaInput partidaInput) {

        return modelMapper.map(partidaInput, Partida.class);
    }

    public void copyToDomainObject(PartidaInput partidaInput, Partida partida) {
        modelMapper.map(partidaInput, partida);
    }
}
