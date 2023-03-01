package com.domain.service;

import com.domain.exception.ApostaNaoEncontradaException;
import com.domain.model.Aposta;
import com.domain.repository.ApostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroApostaService {

    private static final String MSG_APOSTA_EM_USO
            = "Aposta de código %d não pode ser removido, pois está em uso";

    @Autowired
    private ApostaRepository apostaRepository;

    public Aposta salvar(Aposta aposta) {
        return apostaRepository.save(aposta);
    }

    public Aposta buscarOuFalhar(Long apostaId) {
        return apostaRepository.findById(apostaId)
                .orElseThrow(() -> new ApostaNaoEncontradaException(apostaId));
    }
}
