package com.ibandorta.seguros.seguros.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Seguros")
                        .version("1.0")
                        .description("Documentacion de la API para gestión de seguros. ")
                        .contact(new Contact()
                                .name("Iban Dorta")
                                .email("ibandorta@gmail.com")));
    }

}
