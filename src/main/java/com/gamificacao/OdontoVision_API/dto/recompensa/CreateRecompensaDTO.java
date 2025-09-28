package com.gamificacao.OdontoVision_API.dto.recompensa;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.math.BigDecimal;

/** DTO de criação de recompensa. */
public record CreateRecompensaDTO(
        @NotBlank @Size(max = 255) String descricao,
        @NotNull  @Positive        Integer pontosNecessarios,
        @PositiveOrZero            Integer quantidadeDisponivel,
        LocalDate                  dataExpiracao
) {}
