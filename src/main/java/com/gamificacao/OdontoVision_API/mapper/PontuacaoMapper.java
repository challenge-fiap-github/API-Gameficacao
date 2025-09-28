package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.gamificacao.CreditoPontosDTO;
import com.gamificacao.OdontoVision_API.dto.gamificacao.LeaderboardItemDTO;
import com.gamificacao.OdontoVision_API.dto.gamificacao.PontuacaoDTO;
import com.gamificacao.OdontoVision_API.mapper.config.MapStructConfig;
import com.gamificacao.OdontoVision_API.model.Pontuacao;
import com.gamificacao.OdontoVision_API.repository.PontuacaoRepository;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface PontuacaoMapper {

    // Entidade -> DTO
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "motivo", expression = "java(null)") // o model não tem 'motivo'
    PontuacaoDTO toDTO(Pontuacao entity);

    // DTO de crédito -> Entidade (service vai setar usuario)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "ciclo_inicial", ignore = true)
    @Mapping(target = "ciclo_final", ignore = true)
    @Mapping(target = "dataRegistro", source = "dataRegistro")
    Pontuacao toEntity(CreditoPontosDTO dto);

    // Projeção para leaderboard -> DTO
    default LeaderboardItemDTO toLeaderboardDTO(PontuacaoRepository.LeaderboardItemView view) {
        if (view == null) return null;
        return new LeaderboardItemDTO(view.getUsuarioId(), view.getNome(), view.getTotal());
    }
}
