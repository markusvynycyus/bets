package com.domain.service;


import com.domain.exception.EntidadeEmUsoException;
import com.domain.exception.PermissaoNaoEncontradaException;
import com.domain.exception.TimeNaoEncontradoException;
import com.domain.model.Permissao;
import com.domain.model.Time;
import com.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CadastroPermissaoService {

    private static final String MSG_PERMISSAO_EM_USO
            = "Permissao de código %d não pode ser removida, pois está em uso";


    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }


    @Transactional
    public void excluir(Long permissaoId) {
        try {
            permissaoRepository.deleteById(permissaoId);
            permissaoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PermissaoNaoEncontradaException(permissaoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PERMISSAO_EM_USO, permissaoId));
        }
    }

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}
