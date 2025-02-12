package it.asansonne.storybe.model.jpa;

import it.asansonne.storybe.model.Models;
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
 * The type Story.
 */
@Builder
@Entity
@Table(name = "story")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class StoryJpa implements Models {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @ToString.Exclude
  private Long id;

  @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "UUID")
  private UUID uuid;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT", nullable = false)
  private String description;

  @Column(name = "date_creation", columnDefinition = "BIGINT", nullable = false)
  private Long creationDate;

  @Column(name = "date_last_update", columnDefinition = "BIGINT")
  private Long lastEditDate;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "is_completed", nullable = false)
  private Boolean isCompleted;

  @ManyToOne
  @JoinColumn(name = "master_id", nullable = false)
  private MasterJpa author;
}
