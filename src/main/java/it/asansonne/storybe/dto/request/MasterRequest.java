package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Master request.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Master Request DTO")
public class MasterRequest implements Dto {
  @NotBlank(message = "Master username must not be null or empty")
  @Schema(
      description = "Master username",
      name = "username",
      type = "String",
      example = "mrossi")
  private String username;

  @NotBlank(message = "Master password must not be null or empty")
  @Size(
      min = 8,
      max = 32,
      message = "The master password must be between 8 and 32 characters")
  @Schema(
      description = "Master password",
      name = "password",
      type = "String",
      example = "password")
  private String password;

  @NotBlank(message = "Master email must not be null or empty")
  @Email(message = "Invalid master email format")
  @Size(
      min = 10,
      max = 100,
      message = "The master email must be between 10 and 100 characters")
  @Schema(
      description = "Master email",
      name = "email",
      type = "String",
      example = "example@mail.it")
  private String email;

  @NotBlank(message = "Master surname must not be null or empty")
  @Schema(
      description = "Master surname",
      name = "lastname",
      type = "String",
      example = "Rossi")
  private String lastname;

  @NotBlank(message = "Master name must not be null or empty")
  @Schema(
      description = "Master name",
      name = "firstname",
      type = "String",
      example = "Mario")
  private String firstname;

  @NotNull(message = "Master status must not be null or empty")
  @Schema(
      description = "Master status",
      name = "isActive",
      type = "Boolean",
      example = "true")
  private Boolean isActive;

  @JsonProperty("groups")
  @Schema(
      description = "Master status",
      name = "groups",
      type = "List<GroupRequest>")
  private List<GroupRequest> groups;

  @Schema(
      description = "Master biography",
      name = "biography",
      type = "String",
      example = "This is a biography")
  private String biography;

  @Schema(
      hidden = true,
      description = "Master profile image",
      name = "profileImage",
      type = "byte[]")
  private byte[] profileImage;
}