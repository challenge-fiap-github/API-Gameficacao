package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.UsuarioPlano;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioPlanoRepository extends BaseRepository<UsuarioPlano, Long> {
    Page<UsuarioPlano> findByUsuarioId(Long usuarioId, Pageable pageable);
    Page<UsuarioPlano> findByPlanoId(Long planoId, Pageable pageable);
}