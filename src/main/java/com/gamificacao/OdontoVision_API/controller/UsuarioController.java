package com.gamificacao.OdontoVision_API.controller;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.hateoas.assembler.UsuarioModelAssembler;
import com.gamificacao.OdontoVision_API.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping(value = "/api/v1/usuarios", produces = "application/json")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler usuarioAssembler;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<EntityModel<UsuarioDTO>> criar(@Valid @RequestBody CreateUsuarioDTO dto) {
        var salvo = usuarioService.criar(dto);
        var model = usuarioAssembler.toModel(salvo);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<EntityModel<UsuarioDTO>> atualizar(@PathVariable Long id, @Valid @RequestBody UpdateUsuarioDTO dto) {
        var atualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuarioAssembler.toModel(atualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> buscarPorId(@PathVariable Long id) {
        var dto = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioAssembler.toModel(dto));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UsuarioDTO>>> listar(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "q", required = false) String query
    ) {
        Page<UsuarioDTO> page = usuarioService.listar(pageable);
        var paged = PagedModel.of(
                page.map(usuarioAssembler::toModel).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages())
        );
        paged.add(linkTo(methodOn(UsuarioController.class).listar(pageable, query)).withSelfRel());
        return ResponseEntity.ok(paged);
    }
}

