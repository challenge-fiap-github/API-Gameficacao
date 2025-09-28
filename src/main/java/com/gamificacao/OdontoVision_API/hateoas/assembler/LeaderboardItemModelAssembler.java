package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.dto.gamificacao.LeaderboardItemDTO;
import com.gamificacao.OdontoVision_API.controller.GamificacaoController;
import com.gamificacao.OdontoVision_API.controller.UsuarioController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LeaderboardItemModelAssembler implements RepresentationModelAssembler<LeaderboardItemDTO, EntityModel<LeaderboardItemDTO>> {

    @Override
    public EntityModel<LeaderboardItemDTO> toModel(LeaderboardItemDTO dto) {
        return EntityModel.of(dto,
                // self é o próprio ranking; não há recurso individual
                linkTo(methodOn(GamificacaoController.class).leaderboard(null)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).buscarPorId(dto.usuarioId())).withRel("usuario"),
                linkTo(methodOn(GamificacaoController.class).saldo(dto.usuarioId())).withRel("saldo"),
                linkTo(methodOn(GamificacaoController.class).extrato(dto.usuarioId(), null)).withRel("extrato")
        );
    }

    @Override
    public CollectionModel<EntityModel<LeaderboardItemDTO>> toCollectionModel(Iterable<? extends LeaderboardItemDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(GamificacaoController.class).leaderboard(null)).withRel(IanaLinkRelations.SELF));
    }
}
