package it.asansonne.storybe.ccsr.repository.node4j;

import it.asansonne.storybe.model.node4j.StoryNode;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface StoryNodeRepository extends Neo4jRepository<StoryNode, Long> {
  Optional<StoryNode> findByUuid(UUID uuid);
}
