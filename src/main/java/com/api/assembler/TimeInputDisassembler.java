package com.api.assembler;

import com.api.dto.input.TimeInput;
import com.domain.model.Time;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Time toDomainObject(TimeInput timeInput) {
        return modelMapper.map(timeInput, Time.class);
    }

    public void copyToDomainObject(TimeInput timeInput, Time time) {
        modelMapper.map(timeInput, time);
    }
}
