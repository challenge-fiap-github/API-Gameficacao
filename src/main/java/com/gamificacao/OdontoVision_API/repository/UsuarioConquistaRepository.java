package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.UsuarioConquista;
import com.gamificacao.OdontoVision_API.model.id.UsuarioConquistaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioConquistaRepository extends BaseRepository<UsuarioConquista, UsuarioConquistaId> {
    Page<UsuarioConquista> findByUsuarioId(Long usuarioId, Pageable pageable);
}