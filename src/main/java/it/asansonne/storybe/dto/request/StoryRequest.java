package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Story request.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Story Request DTO")
public class StoryRequest implements Dto {
  @NotBlank(message = "Story title must be not empty")
  @Size(min = 1, max = 255, message = "Story title must be between 1 and 255 characters")
  @Schema(
      description = "Story title",
      name = "title",
      type = "String",
      example = "This is a title")
  private String title;

  @NotBlank(message = "Story description must be not null")
  @Schema(
      description = "Story description",
      name = "description",
      type = "String",
      example = "This is a story description")
  private String description;
}
