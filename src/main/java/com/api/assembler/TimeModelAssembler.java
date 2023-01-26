package com.api.assembler;

import com.api.dto.TimeDTO;
import com.domain.model.Time;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeModelAssembler {
    @Autowired
    private ModelMapper modelMaper;

    //--> retorna uma Instância de Restaurante Model
    public TimeDTO toModel(Time time) {
        return modelMaper.map(time, TimeDTO.class);
    }

    public List<TimeDTO> toCollectionModel(List<Time> times) {
        return times.stream()
                .map(time -> toModel(time))
                .collect(Collectors.toList());
    }
}