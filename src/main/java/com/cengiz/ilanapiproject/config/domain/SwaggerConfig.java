package com.cengiz.ilanapiproject.config.domain;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


/**
 * @author Cengiz ÖZDEMİR
 * @created 13/11/2024
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ilan API",
                version = "v1",
                description = "ilan ile ilgili tüm işlemleri yapan API"
        )
)
public class SwaggerConfig {
}
