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
        // TODO: se for usar segurança depois: entity.setSenha(passwordEncoder.encode(dto.senha()));
        Usuario salvo = usuarioRepository.save(entity);
        return usuarioMapper.toDTO(salvo);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UpdateUsuarioDTO dto) {
        Usuario entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        // se e-mail/cpf forem alterados, garantir unicidade
        if (dto.email() != null || dto.cpf() != null) {
            boolean conflito = usuarioRepository.existsByEmailOrCpf(
                    dto.email() != null ? dto.email() : entity.getEmail(),
                    dto.cpf() != null ? dto.cpf() : entity.getCpf()
            ) && !(entity.getEmail().equals(dto.email()) || entity.getCpf().equals(dto.cpf()));
            if (conflito) throw new ConflictException("E-mail ou CPF já cadastrado");
        }
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
        return usuarioRepository.findAllWithEndereco(pageable).map(usuarioMapper::toDTO);
    }
}
