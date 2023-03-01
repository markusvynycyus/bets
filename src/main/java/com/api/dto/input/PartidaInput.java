package com.api.dto.input;


import com.api.dto.TimeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PartidaInput {

    @NotBlank
    private TimeDTO timeCasa;

    @NotBlank
    private TimeDTO timeFora;
}
