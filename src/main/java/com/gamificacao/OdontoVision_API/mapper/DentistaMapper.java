package com.gamificacao.OdontoVision_API.mapper;

import com.gamificacao.OdontoVision_API.dto.dentista.CreateDentistaDTO;
import com.gamificacao.OdontoVision_API.dto.dentista.DentistaDTO;
import com.gamificacao.OdontoVision_API.model.Dentista;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DentistaMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id",            source = "id")
    @Mapping(target = "nome",          source = "nome")
    @Mapping(target = "cro",           source = "cro")
    @Mapping(target = "especialidade", source = "especialidade")
    @Mapping(target = "telefone",      source = "telefone")
    @Mapping(target = "email",         source = "email")
    DentistaDTO toDTO(Dentista entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nome",          source = "nome")
    @Mapping(target = "cro",           source = "cro")
    @Mapping(target = "especialidade", source = "especialidade")
    @Mapping(target = "telefone",      source = "telefone")
    @Mapping(target = "email",         source = "email")
    Dentista toEntity(CreateDentistaDTO dto);

}
