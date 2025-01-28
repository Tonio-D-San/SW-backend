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
 * The type Topic update request.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Topic Update Request DTO")
public class TopicUpdateRequest implements Dto {
  @Size(min = 1, max = 255, message = "Topic title must be between 1 and 255 characters")
  @Schema(
      description = "Topic title",
      name = "title",
      type = "String",
      example = "This is a title")
  private String title;

  @Schema(
      description = "Topic problem description",
      name = "problem",
      type = "String",
      example = "This is a topic problem")
  private String problem;

  @Schema(
      description = "Topic solution description",
      name = "solution",
      type = "String",
      example = "This is a topic solution")
  private String solution;
}
