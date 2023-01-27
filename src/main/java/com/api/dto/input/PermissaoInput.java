package com.api.dto.input;

import javax.validation.constraints.NotBlank;

public class PermissaoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
