package com.gamificacao.OdontoVision_API.dto.recompensa;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateRecompensaDTO(
        @Size(max = 255) String descricao,
        @Positive        Integer pontosNecessarios,
        @PositiveOrZero  Integer quantidadeDisponivel,
        LocalDate        dataExpiracao
) {}
