package com.almoxarifado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.almoxarifado.repository")
public class AlmoxarifadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmoxarifadoApplication.class, args);
	}

}
