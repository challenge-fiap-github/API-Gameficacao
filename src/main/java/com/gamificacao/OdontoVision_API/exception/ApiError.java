package com.gamificacao.OdontoVision_API.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

/**
 * Modelo padrão de erro exposto pela API.
 */
public class ApiError {

    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<String> details;

    public ApiError(HttpStatus status, String message, String path, List<String> details) {
        this.timestamp = Instant.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.details = details == null ? List.of() : List.copyOf(details);
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<String> getDetails() {
        return details;
    }
}
