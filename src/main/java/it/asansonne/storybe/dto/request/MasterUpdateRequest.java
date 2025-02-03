package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
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
public class MasterUpdateRequest implements Dto {
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