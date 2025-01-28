package it.asansonne.storybe.model.jpa;

import it.asansonne.storybe.model.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Topic.
 */
@Builder
@Entity
@Table(name = "topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Topic implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @ToString.Exclude
  private Long id;

  @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "UUID")
  private UUID uuid;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "description_problem", columnDefinition = "TEXT", nullable = false)
  private String problem;

  @Column(name = "description_solution", columnDefinition = "TEXT")
  private String solution;

  @Column(name = "date_creation", columnDefinition = "BIGINT", nullable = false)
  private Long creationDate;

  @Column(name = "date_last_update", columnDefinition = "BIGINT")
  private Long lastEditDate;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "is_open", nullable = false)
  private Boolean isOpen;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = false)
  private Person author;
}
