package com.studentmanagement.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Student Management API", version = "1.0", description = "API for managing students"))
public class OpenApiConfig {
    // Swagger UI will automatically pick up all APIs in this package
}