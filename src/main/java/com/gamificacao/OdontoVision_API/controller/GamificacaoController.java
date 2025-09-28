package com.gamificacao.OdontoVision_API.controller;

import com.gamificacao.OdontoVision_API.dto.gamificacao.*;
import com.gamificacao.OdontoVision_API.hateoas.assembler.LeaderboardItemModelAssembler;
import com.gamificacao.OdontoVision_API.hateoas.assembler.PontuacaoModelAssembler;
import com.gamificacao.OdontoVision_API.hateoas.assembler.SaldoModelAssembler;
import com.gamificacao.OdontoVision_API.service.GamificacaoService;
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
@RequestMapping(value = "/api/v1/gamificacao", produces = "application/json")
@RequiredArgsConstructor
public class GamificacaoController {

    private final GamificacaoService gamificacaoService;
    private final PontuacaoModelAssembler pontuacaoAssembler;
    private final LeaderboardItemModelAssembler leaderboardAssembler;
    private final SaldoModelAssembler saldoAssembler;

    @PostMapping(path = "/creditos", consumes = "application/json")
    public ResponseEntity<EntityModel<PontuacaoDTO>> lancar(@Valid @RequestBody CreditoPontosDTO dto) {
        var lancado = gamificacaoService.lancar(dto);
        var model = pontuacaoAssembler.toModel(lancado);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @GetMapping("/usuarios/{usuarioId}/saldo")
    public ResponseEntity<EntityModel<SaldoDTO>> saldo(@PathVariable Long usuarioId) {
        var dto = gamificacaoService.saldo(usuarioId);
        return ResponseEntity.ok(saldoAssembler.toModel(dto));
    }

    @GetMapping("/usuarios/{usuarioId}/movimentacoes")
    public ResponseEntity<PagedModel<EntityModel<PontuacaoDTO>>> extrato(
            @PathVariable Long usuarioId,
            @PageableDefault(size = 20, sort = "dataRegistro", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<PontuacaoDTO> page = gamificacaoService.extrato(usuarioId, pageable);
        var paged = PagedModel.of(
                page.map(pontuacaoAssembler::toModel).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages())
        );
        // link self
        paged.add(linkTo(methodOn(GamificacaoController.class).extrato(usuarioId, pageable)).withSelfRel());
        return ResponseEntity.ok(paged);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<PagedModel<EntityModel<LeaderboardItemDTO>>> leaderboard(
            @PageableDefault(size = 20) Pageable pageable
    ) {
        Page<LeaderboardItemDTO> page = gamificacaoService.leaderboard(pageable);
        var paged = PagedModel.of(
                page.map(leaderboardAssembler::toModel).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages())
        );
        paged.add(linkTo(methodOn(GamificacaoController.class).leaderboard(pageable)).withSelfRel());
        return ResponseEntity.ok(paged);
    }

    @PostMapping(path = "/resgates", consumes = "application/json")
    public ResponseEntity<EntityModel<PontuacaoDTO>> resgatar(@Valid @RequestBody ResgateRequestDTO dto) {
        var debito = gamificacaoService.resgatar(dto);
        var model = pontuacaoAssembler.toModel(debito);
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }
}
