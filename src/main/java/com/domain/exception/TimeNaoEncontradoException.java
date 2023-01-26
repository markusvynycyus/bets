package com.domain.exception;

public class TimeNaoEncontradoException extends EntidadeNaoEncontradaException{

        private static final long serialVersionUID = 1L;

        public TimeNaoEncontradoException(String mensagem) {
            super(mensagem);
        }

        public TimeNaoEncontradoException(Long timeId) {
            this(String.format("Não existe um cadastro de time com código %d", timeId));
        }

}

