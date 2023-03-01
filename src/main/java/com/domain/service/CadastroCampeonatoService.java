package com.domain.service;

import com.domain.exception.CampeonatoNaoEncontradoException;
import com.domain.exception.EntidadeEmUsoException;
import com.domain.model.Campeonato;
import com.domain.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroCampeonatoService {

    private static final String MSG_CAMPEONATO_EM_USO
            = "Campeonato de código %d não pode ser removido, pois está em uso";

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public Campeonato salvar(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    @Transactional
    public void excluir(Long campeonatoId) {
        try {
            campeonatoRepository.deleteById(campeonatoId);
            campeonatoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new CampeonatoNaoEncontradoException(campeonatoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CAMPEONATO_EM_USO, campeonatoId));
        }
    }

    public Campeonato buscarOuFalhar(Long campeonatoId) {
        return campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException(campeonatoId));
    }

}
