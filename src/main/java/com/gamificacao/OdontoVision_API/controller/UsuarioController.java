package com.gamificacao.OdontoVision_API.controller;

import com.gamificacao.OdontoVision_API.dto.usuario.CreateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UpdateUsuarioDTO;
import com.gamificacao.OdontoVision_API.dto.usuario.UsuarioDTO;
import com.gamificacao.OdontoVision_API.hateoas.assembler.UsuarioModelAssembler;
import com.gamificacao.OdontoVision_API.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler usuarioModelAssembler;
    private final PagedResourcesAssembler<UsuarioDTO> pagedResourcesAssembler;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioModelAssembler usuarioModelAssembler,
                             PagedResourcesAssembler<UsuarioDTO> pagedResourcesAssembler) {
        this.usuarioService = usuarioService;
        this.usuarioModelAssembler = usuarioModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioDTO>> criar(@Valid @RequestBody CreateUsuarioDTO dto) {
        UsuarioDTO usuario = usuarioService.criar(dto);
        EntityModel<UsuarioDTO> model = usuarioModelAssembler.toModel(usuario);
        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UsuarioDTO>>> listar(Pageable pageable) {
        Page<UsuarioDTO> pagina = usuarioService.listar(pageable);
        PagedModel<EntityModel<UsuarioDTO>> model = pagedResourcesAssembler.toModel(pagina, usuarioModelAssembler);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> buscar(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscar(id);
        return ResponseEntity.ok(usuarioModelAssembler.toModel(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> atualizar(@PathVariable Long id,
                                                             @Valid @RequestBody UpdateUsuarioDTO dto) {
        UsuarioDTO usuario = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuarioModelAssembler.toModel(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
