package it.asansonne.storybe.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.response.MasterResponse;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.MasterJpa;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * The type Master mapper.
 */
@Component
public class MasterModelMapper implements RequestModelMapper<MasterRequest, MasterJpa>,
    ResponseModelMapper<MasterJpa, MasterResponse> {

  @Override
  public MasterJpa toModel(MasterRequest dto) {
    if (dto == null) {
      return null;
    }
    return MasterJpa.builder()
        .biography(dto.getBiography())
        .build();
  }

  @Override
  public MasterResponse toDto(MasterJpa model) {
    if (model == null) {
      return null;
    }
    return MasterResponse.builder()
        .id(model.getUuid())
        .email(model.getEmail())
        .firstName(model.getName())
        .lastName(model.getSurname())
        .biography(model.getBiography())
        .enabled(model.getIsActive())
        .build();
  }

  @Override
  public MasterJpa dtoToModelResponse(MasterResponse dto) {
    if (dto == null) {
      return null;
    }
    return MasterJpa.builder()
        .uuid(dto.getId())
        .email(dto.getEmail())
        .name(dto.getFirstName())
        .surname(dto.getLastName())
        .biography(dto.getBiography())
        .isActive(dto.getEnabled())
        .build();
  }

  /**
   * Json to dto list.
   *
   * @param json the json
   * @return the list
   */
  public List<MasterResponse> jsonToDto(String json) {
    try {
      return List.of(new ObjectMapper().readValue(json, MasterResponse[].class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException();
    }
  }
}
