package com.domain.service;

import com.domain.exception.CampeonatoNaoEncontradoException;
import com.domain.model.Campeonato;
import com.domain.repository.CampeonatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCampeonatoService {

    private static final String MSG_TIME_EM_USO
            = "Time de código %d não pode ser removido, pois está em uso";

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public Campeonato salvar(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato buscarOuFalhar(Long campeonatoId) {
        return campeonatoRepository.findById(campeonatoId)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException(campeonatoId));
    }

}
