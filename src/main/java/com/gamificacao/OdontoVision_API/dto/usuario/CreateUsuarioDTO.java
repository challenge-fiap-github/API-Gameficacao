package com.gamificacao.OdontoVision_API.dto.usuario;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/** DTO de entrada para criação de usuário (não expõe id/senha criptografada). */
public record CreateUsuarioDTO(
        @NotBlank(message = "{usuario.nome.obrigatorio}")
        @Size(max = 100, message = "{usuario.nome.tamanho}")
        String nome,

        @NotBlank(message = "{usuario.email.obrigatorio}")
        @Email(message = "{usuario.email.invalido}")
        @Size(max = 100, message = "{usuario.email.tamanho}")
        String email,

        @NotBlank(message = "{usuario.senha.obrigatorio}")
        @Size(min = 6, max = 100, message = "{usuario.senha.tamanho}")
        String senha,

        @Past(message = "{usuario.dataNasci.past}")
        LocalDate dataNascimento,

        @NotBlank(message = "{usuario.cpf.obrigatorio}")
        @Pattern(regexp = "\\d{11}", message = "{usuario.cpf.formato}")
        String cpf,

        @Size(max = 15, message = "{usuario.telefone.tamanho}")
        String telefone
) {}
