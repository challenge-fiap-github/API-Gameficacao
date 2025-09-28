package com.gamificacao.OdontoVision_API.dto.gamificacao;

import jakarta.validation.constraints.*;

public record ResgateRequestDTO(
        @NotNull Long usuarioId,
        @NotNull Long recompensaId
) {}
