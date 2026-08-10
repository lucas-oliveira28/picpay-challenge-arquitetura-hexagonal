package io.github.lucasoliveira28;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "io.github.lucasoliveira28") // Garante que ache as @Entity
@EnableJpaRepositories(basePackages = "io.github.lucasoliveira28") // Garante que ache as interfaces @Repository
@ComponentScan(basePackages = "io.github.lucasoliveira28") // Garante que ache os mappers e adapters
public class TestJpaConfig {
}
