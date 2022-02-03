package edu.doggy228.loyaltyexch.lsemu;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "edu.doggy228.loyaltyexch.lsemu")
public class LSEmuApplication {

	public static void main(String[] args) {
		SpringApplication.run(LSEmuApplication.class, args);
	}

	@Bean
	public OpenAPI lsemuOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Loyalty System (emu) API")
						.description("Емуляція роботи систем лояльності.")
						.version("v0.1.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT").description("Токен доступу (ІдентЛС:ІдентКористувача).")));
	}

	@Bean
	public GroupedOpenApi lsemuOpenApi() {
		return GroupedOpenApi.builder()
				.group("Loyalty System (emu) API")
				.packagesToScan("edu.doggy228.loyaltyexch.lsemu.api.v1.lsemu")
				.build();
	}
}
