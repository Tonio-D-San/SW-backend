package it.asansonne.storybe.mapper.impl;

import it.asansonne.storybe.dto.request.TopicRequest;
import it.asansonne.storybe.dto.response.TopicResponse;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.Topic;
import org.springframework.stereotype.Component;

/**
 * The type Topic mapper.
 */

@Component
public class TopicModelMapper implements RequestModelMapper<TopicRequest, Topic>,
    ResponseModelMapper<Topic, TopicResponse> {

  @Override
  public Topic toModel(TopicRequest dto) {
    if (dto == null) {
      return null;
    }
    return Topic.builder()
        .title(dto.getTitle())
        .problem(dto.getProblem())
        .solution(dto.getSolution())
        .isOpen(dto.getSolution() == null)
        .build();
  }

  @Override
  public TopicResponse toDto(Topic model) {
    if (model == null) {
      return null;
    }

    TopicResponse dto = TopicResponse.builder()
        .uuid(model.getUuid())
        .title(model.getTitle())
        .problem(model.getProblem())
        .solution(model.getSolution())
        .creationDate(model.getCreationDate())
        .lastEditDate(model.getLastEditDate())
        .isActive(model.getIsActive())
        .isOpen(model.getIsOpen())
        .build();
    dto.setPersonResponse(new PersonModelMapper().toDto(model.getAuthor()));
    return dto;
  }

  @Override
  public Topic dtoToModelResponse(TopicResponse dto) {
    if (dto == null) {
      return null;
    }
    return Topic.builder()
        .title(dto.getTitle())
        .problem(dto.getProblem())
        .solution(dto.getSolution())
        .creationDate(dto.getCreationDate())
        .lastEditDate(dto.getLastEditDate())
        .isActive(dto.getIsActive())
        .isOpen(dto.getIsOpen())
        .build();
  }
}
