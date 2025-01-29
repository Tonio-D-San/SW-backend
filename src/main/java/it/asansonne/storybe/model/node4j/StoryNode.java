package it.asansonne.storybe.model.node4j;

import it.asansonne.storybe.model.Nodes;
import it.asansonne.storybe.model.jpa.Person;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("storyNode")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class StoryNode implements Nodes {
  @Id
  @GeneratedValue
  @ToString.Exclude
  private String id;

  @Property(name = "uuid")
  private UUID uuid;

  @Property(name = "description")
  private String description; // Descrizione del nodo

  @Relationship(type = "HAS_CHOICE", direction = Relationship.Direction.OUTGOING)
  private List<Choice> choices; // Lista di scelte che portano ad altri nodi

  @Version
  private Long version;
}
