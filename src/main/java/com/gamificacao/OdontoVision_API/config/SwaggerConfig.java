package com.gamificacao.OdontoVision_API.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI odontoVisionOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OdontoVision API")
                        .description("API de Gamificação / OdontoVision")
                        .version("v1")
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui/index.html"));
    }
}
