package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.dto.gamificacao.PontuacaoDTO;
import com.gamificacao.OdontoVision_API.controller.GamificacaoController;
import com.gamificacao.OdontoVision_API.controller.UsuarioController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PontuacaoModelAssembler implements RepresentationModelAssembler<PontuacaoDTO, EntityModel<PontuacaoDTO>> {

    @Override
    public EntityModel<PontuacaoDTO> toModel(PontuacaoDTO dto) {
        return EntityModel.of(dto,
                // sem endpoint específico do lançamento, então o "self" aponta para a lista de extrato do usuário
                linkTo(methodOn(GamificacaoController.class).extrato(dto.usuarioId(), null)).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).buscarPorId(dto.usuarioId())).withRel("usuario"),
                linkTo(methodOn(GamificacaoController.class).saldo(dto.usuarioId())).withRel("saldo")
        );
    }

    @Override
    public CollectionModel<EntityModel<PontuacaoDTO>> toCollectionModel(Iterable<? extends PontuacaoDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
