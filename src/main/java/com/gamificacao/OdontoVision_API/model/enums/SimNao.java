package com.gamificacao.OdontoVision_API.model.enums;

public enum SimNao {
    SIM('S'),
    NAO('N');

    private final char codigo;

    SimNao(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public static SimNao fromCodigo(char codigo) {
        for (SimNao value : values()) {
            if (value.codigo == Character.toUpperCase(codigo)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código inválido para SimNao: " + codigo);
    }
}
