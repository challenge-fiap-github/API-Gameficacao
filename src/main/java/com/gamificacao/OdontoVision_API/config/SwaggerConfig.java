package com.gamificacao.OdontoVision_API.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    /** Metadados gerais do OpenAPI */
    @Bean
    public OpenAPI odontovisionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OdontoVision – API de Gamificação")
                        .description("""
                    API REST para gamificação (usuário, pontuação, recompensas) e integração com o portal do dentista.
                    Endpoints em /api/v1/* com HATEOAS e paginação (Pageable).
                    """)
                        .version("v1")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("Equipe OdontoVision")
                                .email("contato@odontovision.example")
                                .url("https://github.com/challenge-fiap-github")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local"),
                        new Server().url("https://api.odontovision.example").description("Prod")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório")
                        .url("https://github.com/challenge-fiap-github/API-Gameficacao"))
                .components(new Components())
                .tags(List.of(
                        new Tag().name("Usuários").description("CRUD e consulta de usuários"),
                        new Tag().name("Gamificação").description("Saldo, extrato, ranking e lançamentos"),
                        new Tag().name("Recompensas").description("Catálogo e resgates")
                ));
    }

    /** Grupo principal (todos os endpoints /api/**) */
    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/**")
                .build();
    }

    /** (Opcional) Grupo separado só da gamificação */
    @Bean
    public GroupedOpenApi gamificacaoGroup() {
        return GroupedOpenApi.builder()
                .group("gamificacao")
                .pathsToMatch("/api/v1/gamificacao/**")
                .build();
    }
}
