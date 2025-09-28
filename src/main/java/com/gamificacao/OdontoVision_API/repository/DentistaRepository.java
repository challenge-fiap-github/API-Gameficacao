package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Dentista;

import java.util.Optional;

public interface DentistaRepository extends BaseRepository<Dentista, Long> {
    boolean existsByCroOrEmail(String cro, String email);
    Optional<Dentista> findByCro(String cro);
}