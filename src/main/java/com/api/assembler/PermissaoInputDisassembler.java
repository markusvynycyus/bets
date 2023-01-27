package com.api.assembler;

import com.api.dto.input.PermissaoInput;
import com.api.dto.input.TimeInput;
import com.domain.model.Permissao;
import com.domain.model.Time;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Permissao toDomainObject(PermissaoInput permissaoInput) {

        return modelMapper.map(permissaoInput, Permissao.class);
    }

    public void copyToDomainObject(PermissaoInput permissaoInput, Permissao permissao) {
        modelMapper.map(permissaoInput, permissao);
    }
}
