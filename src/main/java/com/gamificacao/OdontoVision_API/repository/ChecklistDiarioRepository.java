package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.ChecklistDiario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ChecklistDiarioRepository extends BaseRepository<ChecklistDiario, Long> {
    Page<ChecklistDiario> findByUsuarioIdOrderByDataDesc(Long usuarioId, Pageable pageable);
    boolean existsByUsuarioIdAndData(Long usuarioId, LocalDate data);
}