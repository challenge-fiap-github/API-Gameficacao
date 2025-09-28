package com.gamificacao.OdontoVision_API.dto.gamificacao;

import java.time.LocalDate;

public record PontuacaoDTO(
        Long id,
        Long usuarioId,
        Integer pontos,
        LocalDate dataRegistro,
        String motivo
) {}
