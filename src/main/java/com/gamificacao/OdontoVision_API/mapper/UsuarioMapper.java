package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioEnderecoDTO;
import com.gamificacao.OdontoVision_API.model.EnderecoUsuario;
import com.gamificacao.OdontoVision_API.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Responsável por mapear entidades de {@link Usuario} para DTOs e vice-versa.
 */
@Component
public class UsuarioMapper {

    public Usuario toEntity(CreateUsuarioDTO dto) {
        Usuario usuario = new Usuario(
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.dataNascimento(),
                dto.cpf(),
                dto.telefone()
        );
        EnderecoUsuario endereco = new EnderecoUsuario(
                dto.logradouro(),
                dto.numero(),
                dto.cidade(),
                dto.estado(),
                dto.cep(),
                dto.complemento()
        );
        usuario.definirEndereco(endereco);
        return usuario;
    }

    public void copy(UpdateUsuarioDTO dto, Usuario usuario) {
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());

        EnderecoUsuario endereco = usuario.getEndereco();
        if (endereco == null) {
            endereco = new EnderecoUsuario();
            usuario.definirEndereco(endereco);
        }
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());
        endereco.setComplemento(dto.complemento());
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioEnderecoDTO endereco = null;
        if (usuario.getEndereco() != null) {
            endereco = new UsuarioEnderecoDTO(
                    usuario.getEndereco().getLogradouro(),
                    usuario.getEndereco().getNumero(),
                    usuario.getEndereco().getCidade(),
                    usuario.getEndereco().getEstado(),
                    usuario.getEndereco().getCep(),
                    usuario.getEndereco().getComplemento()
            );
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataNascimento(),
                usuario.getCpf(),
                usuario.getTelefone(),
                endereco
        );
    }
}
