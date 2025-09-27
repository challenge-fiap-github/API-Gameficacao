package com.gamificacao.OdontoVision_API.dto.usuario;

/**
 * DTO de saída para o endereço do usuário.
 */
public record UsuarioEnderecoDTO(
        String logradouro,
        String numero,
        String cidade,
        String estado,
        String cep,
        String complemento
) {
}
