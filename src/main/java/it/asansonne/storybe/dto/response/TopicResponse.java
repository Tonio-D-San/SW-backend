package it.asansonne.storybe.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Topic response.
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Topic Response DTO")
public class TopicResponse implements Dto {
  @Schema(
      description = "Topic Uuid",
      name = "uuid",
      type = "UUID",
      example = "This is a topic Uuid")
  private UUID uuid;

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

  @Schema(
      description = "Topic creation date",
      name = "creationDate",
      type = "Long",
      example = "1727272231956L")
  private Long creationDate;

  @Schema(
      description = "Topic last edit date",
      name = "lastEditDate",
      type = "Long",
      example = "1727272231956L")
  private Long lastEditDate;

  @Schema(
      description = "Topic's author",
      name = "personResponse",
      type = "PersonResponse")
  private PersonResponse personResponse;

  @Schema(
      description = "Topic status",
      name = "isActive",
      type = "String",
      example = "true")
  private Boolean isActive;

  @Schema(
      description = "Open Topic",
      name = "isOpen",
      type = "String",
      example = "true")
  private Boolean isOpen;
}
