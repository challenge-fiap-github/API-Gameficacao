package com.gamificacao.OdontoVision_API.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(produces = "application/json")
public class RootController {

    /** Redireciona raiz para o Swagger UI. */
    @GetMapping("/")
    public ResponseEntity<Void> home() {
        return ResponseEntity.status(302)
                .header("Location", "/swagger-ui/index.html")
                .build();
    }

    /** Índice HATEOAS da API. */
    @GetMapping("/api")
    public ResponseEntity<RepresentationModel<?>> apiIndex() {
        var model = new RepresentationModel<>();

        model.add(linkTo(methodOn(RootController.class).apiIndex()).withSelfRel());

        // UsuarioController.listar(Pageable pageable, String query)
        model.add(linkTo(methodOn(UsuarioController.class)
                .listar(Pageable.unpaged(), null))
                .withRel("usuarios"));

        // RecompensaController.listarValidas(Pageable pageable, boolean comEstoque)
        model.add(linkTo(methodOn(RecompensaController.class)
                .listarValidas(Pageable.unpaged(), false))
                .withRel("recompensas"));

        // GamificacaoController.leaderboard(Pageable pageable)
        model.add(linkTo(methodOn(GamificacaoController.class)
                .leaderboard(Pageable.unpaged()))
                .withRel("leaderboard"));

        return ResponseEntity.ok(model);
    }
}
