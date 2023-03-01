package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PartidaDTO {

    private Long id;

    private TimeDTO timeCasa;

    private TimeDTO timeFora;

    private LocalDateTime dataPartida;


}
