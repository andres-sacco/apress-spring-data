package com.apress.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.apress.catalog" })
public class ApiCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCatalogApplication.class, args);
	}
}
