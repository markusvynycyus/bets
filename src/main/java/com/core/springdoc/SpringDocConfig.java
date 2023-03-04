package com.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BOLÃO API")
                        .version("v1")
                        .description("REST API de Bolão de Futebol")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.com")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("BetsBolao")
                        .url("https://betsbolao.com")
                );
    }
}