package com.domain.model;

public enum OpcaoResultado {
    CASA("Vitória do time da casa"),
    EMPATE("Empate"),
    FORA("Vitória do time visitante");

    private final String resultado;

    OpcaoResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}