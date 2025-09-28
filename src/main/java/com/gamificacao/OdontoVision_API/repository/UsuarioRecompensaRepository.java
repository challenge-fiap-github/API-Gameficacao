package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.UsuarioRecompensa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRecompensaRepository extends BaseRepository<UsuarioRecompensa, Long> {
    Page<UsuarioRecompensa> findByUsuarioIdOrderByDataResgateDesc(Long usuarioId, Pageable pageable);
}