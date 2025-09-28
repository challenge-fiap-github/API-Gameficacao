package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Diagnostico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiagnosticoRepository extends BaseRepository<Diagnostico, Long> {
    Page<Diagnostico> findByConsultaId(Long consultaId, Pageable pageable);
}