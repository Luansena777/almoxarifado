package com.almoxarifado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaRepositories("com.almoxarifado.repository")
public class AlmoxarifadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlmoxarifadoApplication.class, args);
    }

}
