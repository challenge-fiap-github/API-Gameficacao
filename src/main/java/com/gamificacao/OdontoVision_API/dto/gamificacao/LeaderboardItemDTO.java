package com.gamificacao.OdontoVision_API.dto.gamificacao;

/** Item do ranking (leaderboard). */
public record LeaderboardItemDTO(
        Long usuarioId,
        String nome,
        Integer total
) {}
