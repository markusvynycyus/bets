package com.domain.exception;

public class PartidaNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public PartidaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PartidaNaoEncontradoException(Long partidaId) {
        this(String.format("Não existe um cadastro de Partida com código %d", partidaId));
    }
}
