package com.gamificacao.OdontoVision_API.dto.dentista;

import jakarta.validation.constraints.*;

public record CreateDentistaDTO(
        @NotBlank @Size(max = 100) String nome,
        @NotBlank @Size(max = 20)  String cro,
        @Size(max = 100)           String especialidade,
        @Size(max = 15)            String telefone,
        @NotBlank @Email @Size(max = 100) String email
) {}
