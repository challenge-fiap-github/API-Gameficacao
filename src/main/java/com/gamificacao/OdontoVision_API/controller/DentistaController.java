package com.gamificacao.OdontoVision_API.controller;

import com.gamificacao.OdontoVision_API.dto.dentista.CreateDentistaDTO;
import com.gamificacao.OdontoVision_API.dto.dentista.DentistaDTO;
import com.gamificacao.OdontoVision_API.service.DentistaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/dentistas", produces = "application/json")
@RequiredArgsConstructor
public class DentistaController {

    private final DentistaService dentistaService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DentistaDTO> criar(@Valid @RequestBody CreateDentistaDTO dto) {
        var salvo = dentistaService.criar(dto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dentistaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<DentistaDTO>> listar(
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return ResponseEntity.ok(dentistaService.listar(pageable));
    }
}
