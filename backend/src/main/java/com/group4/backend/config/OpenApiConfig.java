package com.group4.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SCTP Group 4 Backend API") // Set the title
                .version("v1.0.0")                  // Set the version
                .description("API documentation for the Group 4 Backend Project") // Set the description
            );
    }
}
