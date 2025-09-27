package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.exception.BusinessException;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.UsuarioMapper;
import com.gamificacao.OdontoVision_API.model.Usuario;
import com.gamificacao.OdontoVision_API.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> listar(Pageable pageable) {
        return usuarioRepository.findAllBy(pageable)
                .map(usuarioMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscar(Long id) {
        return usuarioMapper.toDTO(buscarEntidade(id));
    }

    @Transactional
    public UsuarioDTO criar(CreateUsuarioDTO dto) {
        validarEmail(dto.email());
        validarCpf(dto.cpf());

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO atualizar(Long id, UpdateUsuarioDTO dto) {
        Usuario usuario = buscarEntidade(id);
        validarEmailAtualizacao(id, dto.email());
        validarCpfAtualizacao(id, dto.cpf());

        usuarioMapper.copy(dto, usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        Usuario usuario = buscarEntidade(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario buscarEntidade(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private void validarEmail(String email) {
        if (usuarioRepository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException("email já cadastrado");
        }
    }

    private void validarEmailAtualizacao(Long id, String email) {
        if (usuarioRepository.existsByEmailIgnoreCaseAndIdNot(email, id)) {
            throw new BusinessException("email já cadastrado");
        }
    }

    private void validarCpf(String cpf) {
        if (usuarioRepository.existsByCpf(cpf)) {
            throw new BusinessException("cpf já cadastrado");
        }
    }

    private void validarCpfAtualizacao(Long id, String cpf) {
        if (usuarioRepository.existsByCpfAndIdNot(cpf, id)) {
            throw new BusinessException("cpf já cadastrado");
        }
    }
}
