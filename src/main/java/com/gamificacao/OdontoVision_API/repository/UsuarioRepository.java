package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
    boolean existsByEmailOrCpf(String email, String cpf);
    Optional<Usuario> findByEmail(String email);

    @EntityGraph(attributePaths = {"endereco"})
    @Query("select u from Usuario u")
    Page<Usuario> findAllWithEndereco(Pageable pageable);
}