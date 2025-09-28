package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.controller.GamificacaoController;
import com.gamificacao.OdontoVision_API.controller.UsuarioController;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<UsuarioDTO, EntityModel<UsuarioDTO>> {

    @Override
    public EntityModel<UsuarioDTO> toModel(UsuarioDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(UsuarioController.class).buscarPorId(dto.id())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar(null, null)).withRel(IanaLinkRelations.COLLECTION),
                linkTo(methodOn(GamificacaoController.class).saldo(dto.id())).withRel("saldo"),
                linkTo(methodOn(GamificacaoController.class).extrato(dto.id(), null)).withRel("extrato"),
                linkTo(methodOn(GamificacaoController.class).leaderboard(null)).withRel("leaderboard")
        );
    }

    @Override
    public CollectionModel<EntityModel<UsuarioDTO>> toCollectionModel(Iterable<? extends UsuarioDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(UsuarioController.class).listar(null, null)).withSelfRel());
    }
}

