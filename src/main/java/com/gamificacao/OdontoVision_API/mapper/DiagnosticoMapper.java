package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.consulta.CreateDiagnosticoDTO;
import com.gamificacao.OdontoVision_API.mapper.config.MapStructConfig;
import com.gamificacao.OdontoVision_API.model.Diagnostico;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface DiagnosticoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "consulta", ignore = true) // service setará a consulta pelo id
    Diagnostico toEntity(CreateDiagnosticoDTO dto);
}
