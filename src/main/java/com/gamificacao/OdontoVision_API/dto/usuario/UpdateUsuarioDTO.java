package com.gamificacao.OdontoVision_API.dto.usuario;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/** DTO de atualização parcial (campos opcionais). */
public record UpdateUsuarioDTO(
        @Size(max = 100, message = "{usuario.nome.tamanho}")
        String nome,

        @Email(message = "{usuario.email.invalido}")
        @Size(max = 100, message = "{usuario.email.tamanho}")
        String email,

        @Size(min = 6, max = 100, message = "{usuario.senha.tamanho}")
        String senha,

        @Past(message = "{usuario.dataNasci.past}")
        LocalDate dataNascimento,

        @Pattern(regexp = "\\d{11}", message = "{usuario.cpf.formato}")
        String cpf,

        @Size(max = 15, message = "{usuario.telefone.tamanho}")
        String telefone
) {}
