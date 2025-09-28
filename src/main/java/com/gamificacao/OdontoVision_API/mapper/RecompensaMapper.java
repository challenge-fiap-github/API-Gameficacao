package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.recompensa.CreateRecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.RecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.UpdateRecompensaDTO;
import com.gamificacao.OdontoVision_API.mapper.config.MapStructConfig;
import com.gamificacao.OdontoVision_API.model.Recompensa;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface RecompensaMapper {

    RecompensaDTO toDTO(Recompensa entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resgates", ignore = true)
    Recompensa toEntity(CreateRecompensaDTO dto);

    void updateEntityFromDto(UpdateRecompensaDTO dto, @MappingTarget Recompensa entity);
}
