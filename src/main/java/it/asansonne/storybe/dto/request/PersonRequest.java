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
 * The type Person request.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Person Request DTO")
public class PersonRequest implements Dto {
  @NotBlank(message = "Person username must not be null or empty")
  @Schema(
      description = "Person username",
      name = "username",
      type = "String",
      example = "mrossi")
  private String username;

  @NotBlank(message = "Person password must not be null or empty")
  @Size(
      min = 8,
      max = 32,
      message = "The person password must be between 8 and 32 characters")
  @Schema(
      description = "Person password",
      name = "password",
      type = "String",
      example = "password")
  private String password;

  @NotBlank(message = "Person email must not be null or empty")
  @Email(message = "Invalid person email format")
  @Size(
      min = 10,
      max = 100,
      message = "The person email must be between 10 and 100 characters")
  @Schema(
      description = "Person email",
      name = "email",
      type = "String",
      example = "example@cybsec.it")
  private String email;

  @NotBlank(message = "Person surname must not be null or empty")
  @Schema(
      description = "Person surname",
      name = "lastname",
      type = "String",
      example = "Rossi")
  private String lastname;

  @NotBlank(message = "Person name must not be null or empty")
  @Schema(
      description = "Person name",
      name = "firstname",
      type = "String",
      example = "Mario")
  private String firstname;

  @NotNull(message = "Person status must not be null or empty")
  @Schema(
      description = "Person status",
      name = "isActive",
      type = "Boolean",
      example = "true")
  private Boolean isActive;

  @JsonProperty("groups")
  @Schema(
      description = "Person status",
      name = "groups",
      type = "List<GroupRequest>")
  private List<GroupRequest> groups;

  @Schema(
      description = "Person biography",
      name = "biography",
      type = "String",
      example = "This is a biography")
  private String biography;

  @Schema(
      hidden = true,
      description = "Person profile image",
      name = "profileImage",
      type = "byte[]")
  private byte[] profileImage;
}