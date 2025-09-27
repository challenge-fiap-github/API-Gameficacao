package com.gamificacao.OdontoVision_API.exception;

/**
 * Exceção para conflitos de estado.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
