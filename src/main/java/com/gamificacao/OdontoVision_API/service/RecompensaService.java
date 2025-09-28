package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.recompensa.CreateRecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.RecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.UpdateRecompensaDTO;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.RecompensaMapper;
import com.gamificacao.OdontoVision_API.model.Recompensa;
import com.gamificacao.OdontoVision_API.repository.RecompensaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RecompensaService {

    private final RecompensaRepository recompensaRepository;
    private final RecompensaMapper recompensaMapper;

    @Transactional
    public RecompensaDTO criar(CreateRecompensaDTO dto) {
        Recompensa entity = recompensaMapper.toEntity(dto);
        Recompensa salvo = recompensaRepository.save(entity);
        return recompensaMapper.toDTO(salvo);
    }

    @Transactional
    public RecompensaDTO atualizar(Long id, UpdateRecompensaDTO dto) {
        Recompensa entity = recompensaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recompensa não encontrada"));
        // aplica somente campos não nulos
        recompensaMapper.updateEntityFromDto(dto, entity);
        return recompensaMapper.toDTO(recompensaRepository.save(entity));
    }

    public RecompensaDTO buscarPorId(Long id) {
        return recompensaRepository.findById(id)
                .map(recompensaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Recompensa não encontrada"));
    }

    public Page<RecompensaDTO> listarValidas(Pageable pageable) {
        return recompensaRepository
                .findByDataExpiracaoAfterOrDataExpiracaoIsNull(LocalDate.now(), pageable)
                .map(recompensaMapper::toDTO);
    }

    public Page<RecompensaDTO> listarComEstoque(Pageable pageable) {
        return recompensaRepository
                .findByQuantidadeDisponivelGreaterThan(0, pageable)
                .map(recompensaMapper::toDTO);
    }
}
