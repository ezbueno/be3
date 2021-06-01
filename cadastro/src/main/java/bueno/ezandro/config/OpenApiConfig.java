package bueno.ezandro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(@Value("${application.description}") String description) {
		return new OpenAPI()
				.info(new Info().title(description).version("1.0").termsOfService("https://swagger.io/license")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}