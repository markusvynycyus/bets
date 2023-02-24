package com.domain.repository.service;

import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Time;
import com.domain.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroTimeService {

    private static final String MSG_TIME_EM_USO
            = "Time de código %d não pode ser removido, pois está em uso";

    @Autowired
    private TimeRepository timeRepository;

    public Time salvar(Time time) {
        return timeRepository.save(time);
    }

    public Time buscarOuFalhar(Long timeId) {
        return timeRepository.findById(timeId)
                .orElseThrow(() -> new TimeNaoEncontradoException(timeId));
    }


}