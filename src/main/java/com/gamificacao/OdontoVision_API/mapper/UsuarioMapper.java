package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.model.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id",       source = "id")
    @Mapping(target = "nome",     source = "nome")
    @Mapping(target = "email",    source = "email")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "cpf",      source = "cpf")
    @Mapping(target = "telefone", source = "telefone")
    UsuarioDTO toDTO(Usuario entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nome",     source = "nome")
    @Mapping(target = "email",    source = "email")
    @Mapping(target = "senha",    source = "senha")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "cpf",      source = "cpf")
    @Mapping(target = "telefone", source = "telefone")
    Usuario toEntity(CreateUsuarioDTO dto);

    // >>> usado no service.atualizar(...)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateUsuarioDTO dto, @MappingTarget Usuario target);
}

