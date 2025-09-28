package com.gamificacao.OdontoVision_API.dto.dentista;

public record DentistaDTO(
        Long id,
        String nome,
        String cro,
        String especialidade,
        String telefone,
        String email
) {}
