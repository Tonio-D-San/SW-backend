package it.asansonne.storybe.model.neo4j;

import it.asansonne.storybe.model.Nodes;
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

@Node("story")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class StoryNeo4j implements Nodes {
  @Id
  @GeneratedValue
  @Property(name = "uuid")
  private UUID uuid;

  @Property(name = "title")
  private String title;

  @Property(name = "description")
  private String description;

  @Property(name = "date_creation")
  private Long creationDate;

  @Property(name = "date_last_update")
  private Long lastEditDate;

  @Version
  private Long version;
}
