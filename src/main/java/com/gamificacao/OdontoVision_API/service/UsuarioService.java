package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.exception.ConflictException;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.UsuarioMapper;
import com.gamificacao.OdontoVision_API.model.Usuario;
import com.gamificacao.OdontoVision_API.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioDTO criar(CreateUsuarioDTO dto) {
        if (usuarioRepository.existsByEmailOrCpf(dto.email(), dto.cpf())) {
            throw new ConflictException("E-mail ou CPF já cadastrado");
        }
        Usuario entity = usuarioMapper.toEntity(dto);
        // se usar segurança depois, encode a senha aqui
        Usuario salvo = usuarioRepository.save(entity);
        return usuarioMapper.toDTO(salvo);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UpdateUsuarioDTO dto) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        // detecta se houve alteração de email/cpf
        boolean sameEmail = (dto.email() == null) || dto.email().equals(entity.getEmail());
        boolean sameCpf   = (dto.cpf()   == null) || dto.cpf().equals(entity.getCpf());
        boolean changed   = !(sameEmail && sameCpf);

        if (changed) {
            String emailParaChecar = dto.email() != null ? dto.email() : entity.getEmail();
            String cpfParaChecar   = dto.cpf()   != null ? dto.cpf()   : entity.getCpf();
            boolean existeOutro = usuarioRepository.existsByEmailOrCpf(emailParaChecar, cpfParaChecar)
                    // se o repo não desconsidera o próprio registro, faça uma verificação simples:
                    && (!emailParaChecar.equals(entity.getEmail()) || !cpfParaChecar.equals(entity.getCpf()));
            if (existeOutro) {
                throw new ConflictException("E-mail ou CPF já cadastrado");
            }
        }

        // aplica somente campos não nulos
        usuarioMapper.updateEntityFromDto(dto, entity);
        Usuario salvo = usuarioRepository.save(entity);
        return usuarioMapper.toDTO(salvo);
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public Page<UsuarioDTO> listar(Pageable pageable) {
        // use o método que você tiver no repositório (pode ser findAll(pageable))
        return usuarioRepository.findAllWithEndereco(pageable).map(usuarioMapper::toDTO);
    }
}
