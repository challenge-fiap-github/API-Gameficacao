package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.dentista.CreateDentistaDTO;
import com.gamificacao.OdontoVision_API.dto.dentista.DentistaDTO;
import com.gamificacao.OdontoVision_API.mapper.config.MapStructConfig;
import com.gamificacao.OdontoVision_API.model.Dentista;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface DentistaMapper {

    DentistaDTO toDTO(Dentista entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    Dentista toEntity(CreateDentistaDTO dto);
}
