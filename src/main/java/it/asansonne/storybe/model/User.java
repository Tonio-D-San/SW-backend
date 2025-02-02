package it.asansonne.storybe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

@Builder
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class User implements Model{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @ToString.Exclude
  private Integer id;

  @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "UUID")
  private UUID uuid;

  @Column(name = "email", unique = true, nullable = false, length = 100)
  private String email;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "surname", nullable = false, length = 50)
  private String surname;

  @Column(name = "biography", columnDefinition = "TEXT")
  private String biography;

  @Column(name = "img_profile", columnDefinition = "BYTEA")
  private byte[] profileImage;

//  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//  @JoinTable(name = "person_group",
//      joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
//      inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
//  private List<Group> groups;
//
//  @JsonIgnore
//  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//  private List<Topic> topic;
//
//  @JsonIgnore
//  @OneToMany(targetEntity = Comment.class, mappedBy = "author", fetch = FetchType.LAZY)
//  private List<Comment> comment;
//}
}
