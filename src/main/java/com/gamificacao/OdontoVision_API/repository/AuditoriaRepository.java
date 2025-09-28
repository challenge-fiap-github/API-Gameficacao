package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Auditoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditoriaRepository extends BaseRepository<Auditoria, Long> {
    Page<Auditoria> findByTabelaAfetadaOrderByDataOperacaoDesc(String tabela, Pageable pageable);
}
