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
 * The type Master response.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Master Response DTO")
public class MasterResponse implements Dto {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @Schema(
      description = "Master uuid",
      name = "masterUuid",
      type = "UUID",
      example = "08fba211-60ca-45fc-b809-86bc2ad81dca")
  private UUID id;

  @Schema(
      description = "Master email",
      name = "email",
      type = "String",
      example = "example@cybsec.it")
  private String email;

  @Schema(
      description = "Master name",
      name = "name",
      type = "String",
      example = "Mario")
  private String firstName;

  @Schema(
      description = "Master surname",
      name = "surname",
      type = "String",
      example = "Rossi")
  private String lastName;

  @Schema(
      description = "Master biography",
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
      description = "Master active toggle",
      name = "isActive",
      type = "String",
      example = "true")
  private Boolean enabled;

  @Schema(
      hidden = true,
      description = "Master profile image",
      name = "profileImage",
      type = "byte[]")
  private byte[] profileImage;
}
