package it.asansonne.storybe.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import it.asansonne.storybe.dto.Dto;
import jakarta.validation.constraints.NotNull;
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
public class PersonGroupRequest implements Dto {
  @JsonProperty("groups")
  @NotNull(message = "Group must not be null or empty")
  @Schema(
      description = "Person status",
      name = "groups",
      type = "List<GroupRequest>")
  private List<GroupRequest> groups;
}
