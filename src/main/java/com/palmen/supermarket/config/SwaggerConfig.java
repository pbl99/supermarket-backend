package com.palmen.supermarket.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SuperMarket-API",               // Título de la API
                version = "1.0",                              // Versión
                description = "Esta es una descripción personalizada de mi API.", // Descripción
                contact = @Contact(
                        name = "Equipo de Soporte",          // Nombre del contacto
                        email = "soporte@example.com",       // Email de contacto
                        url = "https://miempresa.com"        // URL del contacto
                ),
                license = @License(
                        name = "Licencia Apache 2.0",        // Tipo de licencia
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html" // URL de la licencia
                )
        )
)
public class SwaggerConfig {

}
