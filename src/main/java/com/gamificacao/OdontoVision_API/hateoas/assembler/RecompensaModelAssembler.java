package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.controller.GamificacaoController;
import com.gamificacao.OdontoVision_API.controller.RecompensaController;
import com.gamificacao.OdontoVision_API.dto.recompensa.RecompensaDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecompensaModelAssembler implements RepresentationModelAssembler<RecompensaDTO, EntityModel<RecompensaDTO>> {

    @Override
    public EntityModel<RecompensaDTO> toModel(RecompensaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(RecompensaController.class).buscarPorId(dto.id())).withSelfRel(),
                linkTo(methodOn(RecompensaController.class).listarValidas(null, false)).withRel(IanaLinkRelations.COLLECTION),
                linkTo(methodOn(GamificacaoController.class).resgatar(null)).withRel("resgatar")
        );
    }

    @Override
    public CollectionModel<EntityModel<RecompensaDTO>> toCollectionModel(Iterable<? extends RecompensaDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities)
                .add(linkTo(methodOn(RecompensaController.class).listarValidas(null, false)).withSelfRel());
    }
}

