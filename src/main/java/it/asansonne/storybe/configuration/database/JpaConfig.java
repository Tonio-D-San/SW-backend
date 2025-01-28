package it.asansonne.storybe.configuration.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Jpa config.
 */
@Configuration
@EnableJpaRepositories(basePackages = "it.asansonne.storybe.ccsr.repository.jpa")
@EntityScan(basePackages = "it.asansonne.storybe.model")
public class JpaConfig {
}
