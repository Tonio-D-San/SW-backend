package it.asansonne.storybe.model.node4j;

import it.asansonne.storybe.model.Nodes;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Choice {
  @Id
  @GeneratedValue
  private Long id;

  private String text; // Testo della scelta

  @TargetNode
  private StoryNode targetNode; // Nodo di destinazione
}
