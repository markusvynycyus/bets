package com.domain.service;

import com.domain.exception.EntidadeEmUsoException;
import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Time;
import com.domain.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroTimeService {

    private static final String MSG_TIME_EM_USO
            = "Time de código %d não pode ser removido, pois está em uso";

    @Autowired
    private TimeRepository timeRepository;

    public Time salvar(Time time) {
        return timeRepository.save(time);
    }


    @Transactional
    public void excluir(Long timeId) {
        try {
            timeRepository.deleteById(timeId);
            timeRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new TimeNaoEncontradoException(timeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_TIME_EM_USO, timeId));
        }
    }

    public Time buscarOuFalhar(Long timeId) {
        return timeRepository.findById(timeId)
                .orElseThrow(() -> new TimeNaoEncontradoException(timeId));
    }


}