package com.gamificacao.OdontoVision_API.dto.gamificacao;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/** Lançamento de pontos (crédito/débito). */
public record CreditoPontosDTO(
        @NotNull Long usuarioId,
        /** pode ser negativo para débito (resgate) */
        @NotNull @Min(value = -1) Integer pontos,
        @NotBlank @Size(max = 100) String motivo,
        /** se nulo, usar data atual no service */
        LocalDate dataRegistro
) {}
