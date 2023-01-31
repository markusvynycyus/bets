package com.api.assembler;

import com.api.dto.input.CampeonatoInput;
import com.api.dto.input.TimeInput;
import com.domain.model.Campeonato;
import com.domain.model.Time;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampeonatoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Campeonato toDomainObject(CampeonatoInput campeonatoInput ) {

        return modelMapper.map(campeonatoInput, Campeonato.class);
    }

    public void copyToDomainObject(CampeonatoInput campeonatoInput, Campeonato campeonato) {
        modelMapper.map(campeonatoInput, campeonato);
    }
}
