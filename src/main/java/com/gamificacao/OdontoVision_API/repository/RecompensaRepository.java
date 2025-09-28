package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Recompensa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface RecompensaRepository extends BaseRepository<Recompensa, Long> {
    Page<Recompensa> findByDataExpiracaoAfterOrDataExpiracaoIsNull(LocalDate hoje, Pageable pageable);
    Page<Recompensa> findByQuantidadeDisponivelGreaterThan(int min, Pageable pageable);
}