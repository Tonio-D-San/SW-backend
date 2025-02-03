package it.asansonne.storybe.mapper.impl;

import it.asansonne.storybe.dto.request.StoryRequest;
import it.asansonne.storybe.dto.response.StoryResponse;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.StoryJpa;
import org.springframework.stereotype.Component;

/**
 * The type Story mapper.
 */

@Component
public class StoryModelMapper implements RequestModelMapper<StoryRequest, StoryJpa>,
    ResponseModelMapper<StoryJpa, StoryResponse> {

  @Override
  public StoryJpa toModel(StoryRequest dto) {
    if (dto == null) {
      return null;
    }
    return StoryJpa.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .isCompleted(dto.getDescription() == null)
        .build();
  }

  @Override
  public StoryResponse toDto(StoryJpa model) {
    if (model == null) {
      return null;
    }

    StoryResponse dto = StoryResponse.builder()
        .uuid(model.getUuid())
        .title(model.getTitle())
        .description(model.getDescription())
        .creationDate(model.getCreationDate())
        .lastEditDate(model.getLastEditDate())
        .isActive(model.getIsActive())
        .isCompleted(model.getIsCompleted())
        .build();
    dto.setMasterResponse(new MasterModelMapper().toDto(model.getAuthor()));
    return dto;
  }

  @Override
  public StoryJpa dtoToModelResponse(StoryResponse dto) {
    if (dto == null) {
      return null;
    }
    return StoryJpa.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .creationDate(dto.getCreationDate())
        .lastEditDate(dto.getLastEditDate())
        .isActive(dto.getIsActive())
        .isCompleted(dto.getIsCompleted())
        .build();
  }
}
