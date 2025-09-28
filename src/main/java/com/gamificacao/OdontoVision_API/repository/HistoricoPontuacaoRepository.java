package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.HistoricoPontuacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoricoPontuacaoRepository extends BaseRepository<HistoricoPontuacao, Long> {
    Page<HistoricoPontuacao> findByUsuarioIdOrderByDataConsultaDesc(Long usuarioId, Pageable pageable);
}