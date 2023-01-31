package com.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CampeonatoInput {

    @NotBlank
    private String nome;
}
