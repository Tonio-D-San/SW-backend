package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Story update request.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Story Update Request DTO")
public class StoryUpdateRequest implements Dto {
  @Size(min = 1, max = 255, message = "Story title must be between 1 and 255 characters")
  @Schema(
      description = "Story title",
      name = "title",
      type = "String",
      example = "This is a title")
  private String title;

  @Schema(
      description = "Story problem description",
      name = "problem",
      type = "String",
      example = "This is a story problem")
  private String problem;

  @Schema(
      description = "Story solution description",
      name = "solution",
      type = "String",
      example = "This is a story solution")
  private String solution;
}
