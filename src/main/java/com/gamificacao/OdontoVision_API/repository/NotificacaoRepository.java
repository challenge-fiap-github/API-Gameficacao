package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificacaoRepository extends BaseRepository<Notificacao, Long> {
    Page<Notificacao> findByUsuarioIdOrderByDataEnvioDesc(Long usuarioId, Pageable pageable);
}