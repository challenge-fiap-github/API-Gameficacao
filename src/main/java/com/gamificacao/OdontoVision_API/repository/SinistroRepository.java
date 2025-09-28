package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Sinistro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SinistroRepository extends BaseRepository<Sinistro, Long> {
    Page<Sinistro> findByPacienteIdOrderByDataSinistroDesc(Long pacienteId, Pageable pageable);
}