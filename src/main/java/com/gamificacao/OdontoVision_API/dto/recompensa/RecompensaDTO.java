package com.gamificacao.OdontoVision_API.dto.recompensa;

import java.time.LocalDate;

public record RecompensaDTO(
        Long id,
        String descricao,
        Integer pontosNecessarios,
        Integer quantidadeDisponivel,
        LocalDate dataExpiracao
) {}
