package com.gamificacao.OdontoVision_API.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(new Server()
                        .url("/api")
                        .description("Base Path da API")))
                .components(new Components()
                        .addResponses("400", new ApiResponse()
                                .description("Bad Request - Erro de validação")
                                .content(new Content().addMediaType("application/json", new MediaType()
                                        .schema(new Schema<>()
                                                .$ref("#/components/schemas/CustomErrorResponse")))))
                        .addResponses("404", new ApiResponse()
                                .description("Not Found - Recurso não encontrado")
                                .content(new Content().addMediaType("application/json", new MediaType()
                                        .schema(new Schema<>()
                                                .$ref("#/components/schemas/CustomErrorResponse")))))
                        .addResponses("500", new ApiResponse()
                                .description("Internal Server Error - Erro interno")
                                .content(new Content().addMediaType("application/json", new MediaType()
                                        .schema(new Schema<>()
                                                .$ref("#/components/schemas/CustomErrorResponse"))))))
                .info(new Info()
                        .title("OdontoVision API")
                        .description("API RESTful para gestão de gamificação odontológica, consultas e pontuação.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Equipe OdontoVision")
                                .email("contato@odontovision.com")
                                .url("https://odontovision.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa do Projeto")
                        .url("https://odontovision.com/docs"));
    }
}

