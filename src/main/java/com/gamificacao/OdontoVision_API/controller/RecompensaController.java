package com.gamificacao.OdontoVision_API.controller;

import com.gamificacao.OdontoVision_API.dto.recompensa.CreateRecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.RecompensaDTO;
import com.gamificacao.OdontoVision_API.dto.recompensa.UpdateRecompensaDTO;
import com.gamificacao.OdontoVision_API.hateoas.assembler.RecompensaModelAssembler;
import com.gamificacao.OdontoVision_API.service.RecompensaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/recompensas", produces = "application/json")
@RequiredArgsConstructor
public class RecompensaController {

    private final RecompensaService recompensaService;
    private final RecompensaModelAssembler recompensaAssembler;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<EntityModel<RecompensaDTO>> criar(@Valid @RequestBody CreateRecompensaDTO dto) {
        var salvo = recompensaService.criar(dto);
        var model = recompensaAssembler.toModel(salvo);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<EntityModel<RecompensaDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody UpdateRecompensaDTO dto) {
        var atualizado = recompensaService.atualizar(id, dto);
        return ResponseEntity.ok(recompensaAssembler.toModel(atualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RecompensaDTO>> buscarPorId(@PathVariable Long id) {
        var dto = recompensaService.buscarPorId(id);
        return ResponseEntity.ok(recompensaAssembler.toModel(dto));
    }

    @GetMapping("/validas")
    public ResponseEntity<PagedModel<EntityModel<RecompensaDTO>>> listarValidas(
            @PageableDefault(size = 20, sort = "id") Pageable pageable,
            @RequestParam(value = "comEstoque", required = false, defaultValue = "false") boolean comEstoque
    ) {
        Page<RecompensaDTO> page = comEstoque
                ? recompensaService.listarComEstoque(pageable)
                : recompensaService.listarValidas(pageable);

        var paged = PagedModel.of(
                page.map(recompensaAssembler::toModel).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages())
        );
        paged.add(linkTo(methodOn(RecompensaController.class).listarValidas(pageable, comEstoque)).withSelfRel());
        return ResponseEntity.ok(paged);
    }
}

