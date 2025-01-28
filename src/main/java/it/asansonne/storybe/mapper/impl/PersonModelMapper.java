package it.asansonne.storybe.mapper.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.Person;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * The type Person mapper.
 */
@Component
public class PersonModelMapper implements RequestModelMapper<PersonRequest, Person>,
    ResponseModelMapper<Person, PersonResponse> {

  @Override
  public Person toModel(PersonRequest dto) {
    if (dto == null) {
      return null;
    }
    return Person.builder()
        .biography(dto.getBiography())
        .build();
  }

  @Override
  public PersonResponse toDto(Person model) {
    if (model == null) {
      return null;
    }
    return PersonResponse.builder()
        .id(model.getUuid())
        .email(model.getEmail())
        .firstName(model.getName())
        .lastName(model.getSurname())
        .biography(model.getBiography())
        .enabled(model.getIsActive())
        .build();
  }

  @Override
  public Person dtoToModelResponse(PersonResponse dto) {
    if (dto == null) {
      return null;
    }
    return Person.builder()
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
  public List<PersonResponse> jsonToDto(String json) {
    try {
      return List.of(new ObjectMapper().readValue(json, PersonResponse[].class));
    } catch (JsonProcessingException e) {
      throw new RuntimeException();
    }
  }
}
