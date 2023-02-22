package com.domain.exception;

public class ApostaNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public ApostaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ApostaNaoEncontradaException(Long apostaId) {
        this(String.format("Não existe um cadastro de Aposta com código %d", apostaId));
    }
}
