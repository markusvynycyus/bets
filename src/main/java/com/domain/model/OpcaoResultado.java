package com.domain.model;

public enum OpcaoResultado {
    CASA("CASA"),
    EMPATE("EMPATE"),
    FORA("FORA");

    private final String resultado;

    OpcaoResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}