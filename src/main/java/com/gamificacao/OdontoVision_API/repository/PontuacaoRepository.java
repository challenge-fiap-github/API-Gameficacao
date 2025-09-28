package com.gamificacao.OdontoVision_API.repository;

import com.gamificacao.OdontoVision_API.model.Pontuacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PontuacaoRepository extends BaseRepository<Pontuacao, Long> {

    Page<Pontuacao> findByUsuarioIdOrderByDataRegistroDesc(Long usuarioId, Pageable pageable);

    @Query("select coalesce(sum(p.pontos),0) from Pontuacao p where p.usuario.id = :usuarioId")
    Integer sumPontosByUsuario(@Param("usuarioId") Long usuarioId);

    // Leaderboard (soma pontos por usuário)
    @Query("""
           select p.usuario.id as usuarioId, p.usuario.nome as nome, coalesce(sum(p.pontos),0) as total
           from Pontuacao p
           group by p.usuario.id, p.usuario.nome
           order by total desc
           """)
    Page<LeaderboardItemView> leaderboard(Pageable pageable);

    interface LeaderboardItemView {
        Long getUsuarioId();
        String getNome();
        Integer getTotal();
    }
}