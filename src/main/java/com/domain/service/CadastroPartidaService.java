package com.domain.service;

import com.domain.exception.EntidadeEmUsoException;
import com.domain.exception.PartidaNaoEncontradoException;
import com.domain.model.Partida;
import com.domain.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroPartidaService {

    private static final String MSG_PARTIDA_EM_USO
            = "Partida de código %d não pode ser removido, pois está em uso";

    @Autowired
    private PartidaRepository partidaRepository  ;

    public Partida salvar(Partida partida) {
        return partidaRepository.save(partida);
    }

    @Transactional
    public void excluir(Long partidaId) {
        try {
            partidaRepository.deleteById(partidaId);
            partidaRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PartidaNaoEncontradoException(partidaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PARTIDA_EM_USO, partidaId));
        }
    }

    public Partida buscarOuFalhar(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .orElseThrow(() -> new PartidaNaoEncontradoException(partidaId));
    }
}
