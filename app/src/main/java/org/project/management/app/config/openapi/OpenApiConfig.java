package org.project.management.app.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OpenApiProperty.class)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(OpenApiProperty properties) {
        return new OpenAPI()
                .info(getInfo(properties));
    }

    private Info getInfo(OpenApiProperty properties) {
        return new Info()
                .title(properties.projectTitle())
                .description(properties.projectDescription())
                .version(properties.projectVersion())
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("Unlicense")
                .url("https://unlicense.org/");
    }
}