package it.asansonne.storybe.configuration.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * The type Node4J config.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "it.asansonne.storybe.ccsr.repository.neo4j")
public class Neo4jConfig {
}
