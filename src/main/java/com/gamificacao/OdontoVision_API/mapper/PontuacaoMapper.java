package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.gamificacao.CreditoPontosDTO;
import com.gamificacao.OdontoVision_API.dto.gamificacao.LeaderboardItemDTO;
import com.gamificacao.OdontoVision_API.dto.gamificacao.PontuacaoDTO;
import com.gamificacao.OdontoVision_API.model.Pontuacao;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PontuacaoMapper {

    // dto -> entity (lançamento)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "pontos",       source = "pontos")
    @Mapping(target = "dataRegistro", source = "dataRegistro") // se vier nulo, service seta agora()
    Pontuacao toEntity(CreditoPontosDTO dto);

    // entity -> dto
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id",           source = "id")
    @Mapping(target = "usuarioId",    source = "usuario.id")
    @Mapping(target = "pontos",       source = "pontos")
    @Mapping(target = "dataRegistro", source = "dataRegistro")
    PontuacaoDTO toDTO(Pontuacao entity);

    // Caso use consulta nativa que retorna Object[] para ranking
    default LeaderboardItemDTO toLeaderboardDTO(Object[] row) {
        Long usuarioId = (Long) row[0];
        String nome     = (String) row[1];
        Integer total   = row[2] == null ? 0 : ((Number) row[2]).intValue();
        return new LeaderboardItemDTO(usuarioId, nome, total);
    }
}
