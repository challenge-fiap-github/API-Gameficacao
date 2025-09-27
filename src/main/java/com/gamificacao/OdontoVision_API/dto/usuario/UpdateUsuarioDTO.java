package com.gamificacao.OdontoVision_API.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * DTO de entrada para atualização de usuários.
 */
public record UpdateUsuarioDTO(
        @NotBlank(message = "nome é obrigatório") String nome,
        @Email(message = "email inválido") @NotBlank(message = "email é obrigatório") String email,
        @NotBlank(message = "senha é obrigatória") @Size(min = 6, message = "senha deve conter ao menos 6 caracteres") String senha,
        @Past(message = "data de nascimento deve estar no passado") LocalDate dataNascimento,
        @Pattern(regexp = "\\d{11}", message = "cpf deve conter 11 dígitos") String cpf,
        @Pattern(regexp = "\\d{10,11}", message = "telefone deve conter 10 ou 11 dígitos") String telefone,
        @NotBlank(message = "logradouro é obrigatório") String logradouro,
        @NotBlank(message = "número é obrigatório") String numero,
        @NotBlank(message = "cidade é obrigatória") String cidade,
        @NotBlank(message = "estado é obrigatório") String estado,
        @Pattern(regexp = "\\d{8}", message = "cep deve conter 8 dígitos") String cep,
        String complemento
) {
}
