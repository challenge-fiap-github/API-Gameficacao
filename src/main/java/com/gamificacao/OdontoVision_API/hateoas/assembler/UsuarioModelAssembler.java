package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.controller.UsuarioController;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<UsuarioDTO, EntityModel<UsuarioDTO>> {

    @Override
    public EntityModel<UsuarioDTO> toModel(UsuarioDTO dto) {
        EntityModel<UsuarioDTO> model = EntityModel.of(dto);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).buscar(dto.id()))
                .withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).listar(Pageable.unpaged()))
                .withRel("usuarios"));
        return model;
    }
}
