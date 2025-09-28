package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.HistoricoTratamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoricoTratamentoRepository extends BaseRepository<HistoricoTratamento, Long> {
    Page<HistoricoTratamento> findByUsuarioIdOrderByDataDesc(Long usuarioId, Pageable pageable);
}