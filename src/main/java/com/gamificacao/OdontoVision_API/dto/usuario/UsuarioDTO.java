package com.gamificacao.OdontoVision_API.dto.usuario;

import java.time.LocalDate;

/**
 * DTO de saída para usuários.
 */
public record UsuarioDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String cpf,
        String telefone,
        UsuarioEnderecoDTO endereco
) {
}
