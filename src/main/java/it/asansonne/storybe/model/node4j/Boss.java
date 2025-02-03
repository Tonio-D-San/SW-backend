package it.asansonne.storybe.model.node4j;

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
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("MainBoss")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Boss implements Nodes {
  @Id
  @GeneratedValue
  @Property(name = "uuid")
  private UUID uuid;

  @Property(name = "nome")
  private String nome; // Descrizione del nodo

  @Property(name = "potenza")
  private int potenza;

  @Property(name = "abilità")
  private String abilita;

  @Property(name = "descrizione")
  private String descrizione;

//  @Relationship(type = "HAS_CHOICE", direction = Relationship.Direction.OUTGOING)
//  private List<Choice> choices; // Lista di scelte che portano ad altri nodi
//
//  @Version
//  private Long version;
}
