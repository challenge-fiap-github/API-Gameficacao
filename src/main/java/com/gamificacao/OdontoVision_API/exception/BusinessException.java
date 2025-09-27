package com.gamificacao.OdontoVision_API.exception;

/**
 * Exceção de regra de negócio.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
