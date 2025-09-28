package com.gamificacao.OdontoVision_API.hateoas.assembler;

import com.gamificacao.OdontoVision_API.dto.gamificacao.SaldoDTO;
import com.gamificacao.OdontoVision_API.controller.GamificacaoController;
import com.gamificacao.OdontoVision_API.controller.UsuarioController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SaldoModelAssembler implements RepresentationModelAssembler<SaldoDTO, EntityModel<SaldoDTO>> {

    @Override
    public EntityModel<SaldoDTO> toModel(SaldoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(GamificacaoController.class).saldo(dto.usuarioId())).withSelfRel(),
                linkTo(methodOn(GamificacaoController.class).extrato(dto.usuarioId(), null)).withRel("extrato"),
                linkTo(methodOn(UsuarioController.class).buscarPorId(dto.usuarioId())).withRel("usuario")
        );
    }
}
