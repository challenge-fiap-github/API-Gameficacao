package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.dentista.CreateDentistaDTO;
import com.gamificacao.OdontoVision_API.dto.dentista.DentistaDTO;
import com.gamificacao.OdontoVision_API.exception.ConflictException;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.DentistaMapper;
import com.gamificacao.OdontoVision_API.model.Dentista;
import com.gamificacao.OdontoVision_API.repository.DentistaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DentistaService {

    private final DentistaRepository dentistaRepository;
    private final DentistaMapper dentistaMapper;

    @Transactional
    public DentistaDTO criar(CreateDentistaDTO dto) {
        if (dentistaRepository.existsByCroOrEmail(dto.cro(), dto.email())) {
            throw new ConflictException("CRO ou e-mail já cadastrado");
        }
        Dentista salvo = dentistaRepository.save(dentistaMapper.toEntity(dto));
        return dentistaMapper.toDTO(salvo);
    }

    public DentistaDTO buscarPorId(Long id) {
        return dentistaRepository.findById(id)
                .map(dentistaMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Dentista não encontrado"));
    }

    public Page<DentistaDTO> listar(Pageable pageable) {
        return dentistaRepository.findAll(pageable).map(dentistaMapper::toDTO);
    }
}
