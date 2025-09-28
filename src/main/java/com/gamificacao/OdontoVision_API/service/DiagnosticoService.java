package com.gamificacao.OdontoVision_API.service;

import com.gamificacao.OdontoVision_API.dto.consulta.CreateDiagnosticoDTO;
import com.gamificacao.OdontoVision_API.exception.NotFoundException;
import com.gamificacao.OdontoVision_API.mapper.DiagnosticoMapper;
import com.gamificacao.OdontoVision_API.model.Consulta;
import com.gamificacao.OdontoVision_API.model.Diagnostico;
import com.gamificacao.OdontoVision_API.repository.ConsultaRepository;
import com.gamificacao.OdontoVision_API.repository.DiagnosticoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;
    private final ConsultaRepository consultaRepository;
    private final DiagnosticoMapper diagnosticoMapper;

    @Transactional
    public Long registrar(CreateDiagnosticoDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.consultaId())
                .orElseThrow(() -> new NotFoundException("Consulta não encontrada"));
        Diagnostico diag = diagnosticoMapper.toEntity(dto);
        diag.setConsulta(consulta);
        return diagnosticoRepository.save(diag).getId();
    }
}
