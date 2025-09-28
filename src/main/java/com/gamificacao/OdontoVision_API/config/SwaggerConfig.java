package com.gamificacao.OdontoVision_API.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "OdontoVision API",
                version = "v1",
                description = "API de Gamificação / OdontoVision"
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                // **apenas** os seus controllers
                .packagesToScan("com.gamificacao.OdontoVision_API.controller")
                // **apenas** rotas da sua API
                .pathsToMatch("/api/**")
                .build();
    }
}
