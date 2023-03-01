package com.domain.service;

import com.domain.exception.PartidaNaoEncontradoException;
import com.domain.model.Partida;
import com.domain.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPartidaService {

    private static final String MSG_PARTIDA_EM_USO
            = "Partida de código %d não pode ser removido, pois está em uso";

    @Autowired
    private PartidaRepository partidaRepository  ;

    public Partida salvar(Partida partida) {
        return partidaRepository.save(partida);
    }

    public Partida buscarOuFalhar(Long partidaId) {
        return partidaRepository.findById(partidaId)
                .orElseThrow(() -> new PartidaNaoEncontradoException(partidaId));
    }
}
