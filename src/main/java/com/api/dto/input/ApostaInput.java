package com.api.dto.input;

import com.domain.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ApostaInput {

    @NotBlank
    private Long id;

    @NotBlank
    private Usuario usuario;
}
