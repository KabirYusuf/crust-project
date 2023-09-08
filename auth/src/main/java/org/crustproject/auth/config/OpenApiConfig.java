package org.crustproject.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@Configuration
@SecurityScheme(name = "BearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class OpenApiConfig {
    Contact kabir = new Contact()
            .name("Kabir Yusuf")
            .email("kabiryusuf2307@gmail.com")
            .url("kabiryusuf2307@gmail.com");

    @Bean
    public OpenAPI configAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Crust Project Api")
                        .version("V 1.00")
                        .description("The Spring Boot Task Application is a lightweight and " +
                                "efficient project, created with the Spring Boot framework, " +
                                "that offers users a straightforward and user-friendly API for managing tasks, " +
                                "to-do lists, and tracking their progress. It enables users to easily create, " +
                                "read, update, and delete tasks, providing a convenient solution for task management.")
                        .contact(kabir)
                        .termsOfService("......... "));
    }
}