package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.mapper.config.MapStructConfig;
import com.gamificacao.OdontoVision_API.model.Usuario;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "planos", ignore = true)
    @Mapping(target = "consultas", ignore = true)
    Usuario toEntity(CreateUsuarioDTO dto);

    @BeanMapping(ignoreByDefault = false)
    void updateEntityFromDto(UpdateUsuarioDTO dto, @MappingTarget Usuario entity);
}
