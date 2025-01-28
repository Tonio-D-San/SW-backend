package it.asansonne.storybe.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Person response.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Person Response DTO")
public class PersonResponse implements Dto {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @Schema(
      description = "Person uuid",
      name = "personUuid",
      type = "UUID",
      example = "08fba211-60ca-45fc-b809-86bc2ad81dca")
  private UUID id;

  @Schema(
      description = "Person email",
      name = "email",
      type = "String",
      example = "example@cybsec.it")
  private String email;

  @Schema(
      description = "Person name",
      name = "name",
      type = "String",
      example = "Mario")
  private String firstName;

  @Schema(
      description = "Person surname",
      name = "surname",
      type = "String",
      example = "Rossi")
  private String lastName;

  @Schema(
      description = "Person biography",
      name = "biography",
      type = "String",
      example = "This is a biography")
  private String biography;

  @Schema(
      description = "List of groups",
      name = "groups",
      type = "List<GroupResponse>")
  private List<GroupResponse> groups;

  @Schema(
      description = "Person active toggle",
      name = "isActive",
      type = "String",
      example = "true")
  private Boolean enabled;

  @Schema(
      hidden = true,
      description = "Person profile image",
      name = "profileImage",
      type = "byte[]")
  private byte[] profileImage;
}
