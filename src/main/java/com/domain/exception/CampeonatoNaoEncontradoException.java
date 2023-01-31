package com.domain.exception;

import com.domain.model.Campeonato;

public class CampeonatoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CampeonatoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public CampeonatoNaoEncontradoException(Long timeId) {
        this(String.format("Não existe um cadastro de time com código %d", timeId));
    }
}
