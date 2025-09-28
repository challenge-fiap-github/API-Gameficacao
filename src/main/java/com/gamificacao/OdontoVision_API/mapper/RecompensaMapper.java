package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.recompensa.CreateRecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.RecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.UpdateRecompensaDTO;
import com.gamificacao.OdontoVision_API.model.Recompensa;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RecompensaMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id",                   source = "id")
    @Mapping(target = "descricao",            source = "descricao")
    @Mapping(target = "pontosNecessarios",    source = "pontosNecessarios")
    @Mapping(target = "quantidadeDisponivel", source = "quantidadeDisponivel")
    @Mapping(target = "dataExpiracao",        source = "dataExpiracao")
    RecompensaDTO toDTO(Recompensa entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "descricao",            source = "descricao")
    @Mapping(target = "pontosNecessarios",    source = "pontosNecessarios")
    @Mapping(target = "quantidadeDisponivel", source = "quantidadeDisponivel")
    @Mapping(target = "dataExpiracao",        source = "dataExpiracao")
    Recompensa toEntity(CreateRecompensaDTO dto);

    // >>> usado no service.atualizar(...)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateRecompensaDTO dto, @MappingTarget Recompensa target);
}

