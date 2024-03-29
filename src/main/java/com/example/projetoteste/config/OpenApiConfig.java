package com.example.projetoteste.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "API de Celulares",
                version = "1.0",
                description = "API de cadastro de celulares"
        )
)
public class OpenApiConfig {
}
