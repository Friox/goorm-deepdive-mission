package dev.friox.database.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Database ORM API")
                .description("설명")
                .version("v0.0.1");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
