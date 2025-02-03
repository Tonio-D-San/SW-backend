package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Update master request.
 * For activate and deactivate master
 */
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Representation of the Master Update Request DTO")
public class MasterStatusRequest implements Dto {
  @NotNull
  @Schema(
      description = "Master active toggle",
      name = "isActive",
      type = "boolean")
  private Boolean isActive;
}
