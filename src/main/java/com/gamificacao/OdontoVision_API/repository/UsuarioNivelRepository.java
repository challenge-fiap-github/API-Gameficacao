package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.id.UsuarioNivelId;
import com.gamificacao.OdontoVision_API.model.UsuarioNivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioNivelRepository extends BaseRepository<UsuarioNivel, UsuarioNivelId> {
    Page<UsuarioNivel> findByUsuarioId(Long usuarioId, Pageable pageable);
}