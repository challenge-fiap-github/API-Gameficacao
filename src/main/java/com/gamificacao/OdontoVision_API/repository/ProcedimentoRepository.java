package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Procedimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProcedimentoRepository extends BaseRepository<Procedimento, Long> {
    Page<Procedimento> findByNomeProcedimentoContainingIgnoreCase(String termo, Pageable pageable);
}