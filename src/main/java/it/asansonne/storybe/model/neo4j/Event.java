package it.asansonne.storybe.model.neo4j;

import it.asansonne.storybe.model.Nodes;
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

@Node("event")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Event implements Nodes {
  @Id
  @GeneratedValue
  @Property(name = "uuid")
  private UUID uuid;

  @Property(name = "text")
  private String text;

  @Relationship(type = "HAS_CHOICE", direction = Relationship.Direction.OUTGOING)
  private List<Choice> choices; 

  @Version
  private Long version;
}
