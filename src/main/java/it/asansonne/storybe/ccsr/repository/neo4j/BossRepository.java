package it.asansonne.storybe.ccsr.repository.neo4j;

import it.asansonne.storybe.model.neo4j.Boss;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BossRepository extends Neo4jRepository<Boss, UUID> {
  Optional<Boss> findByUuid(UUID uuid);
}
