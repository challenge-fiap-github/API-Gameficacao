package com.gamificacao.OdontoVision_API.dto.consulta;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateDiagnosticoDTO(
        @NotNull Long consultaId,
        @NotBlank @Size(max = 255) String descricao,
        @PastOrPresent LocalDate data
) {}
