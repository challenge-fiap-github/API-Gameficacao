package com.gamificacao.OdontoVision_API.repository;
import com.gamificacao.OdontoVision_API.model.Consulta;
import com.gamificacao.OdontoVision_API.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends BaseRepository<Consulta, Long> {
    Page<Consulta> findByUsuarioIdOrderByDataHoraDesc(Long usuarioId, Pageable pageable);
    Page<Consulta> findByDentistaIdAndDataHoraBetween(Long dentistaId, LocalDateTime ini, LocalDateTime fim, Pageable pageable);

    @Query("select count(c) from Consulta c where c.usuario.id = :usuarioId and c.dataHora between :ini and :fim")
    long countConsultasPeriodo(Long usuarioId, LocalDateTime ini, LocalDateTime fim);
}