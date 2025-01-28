package it.asansonne.storybe.configuration.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Node4J config.
 */
@Configuration
@EnableJpaRepositories(basePackages = "it.asansonne.storybe.ccsr.repository.node4j")
@EntityScan(basePackages = "it.asansonne.storybe.model.node4j")
public class Node4jConfig {
}
