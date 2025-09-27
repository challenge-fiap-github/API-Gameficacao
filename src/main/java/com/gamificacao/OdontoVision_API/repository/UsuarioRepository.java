package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

    @EntityGraph(attributePaths = "endereco")
    Page<Usuario> findAllBy(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "endereco")
    Optional<Usuario> findById(Long id);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Long id);
}
