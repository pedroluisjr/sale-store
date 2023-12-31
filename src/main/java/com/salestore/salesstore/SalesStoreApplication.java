package com.salestore.salesstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Tag(name = "user", description = "User API endpoint")
@OpenAPIDefinition(info = @Info(
		title = "Stores API",
		description = "ERP Store for studying Spring Boot",
		version = "0.0.1"))
@SpringBootApplication
public class SalesStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesStoreApplication.class, args);
	}

}
