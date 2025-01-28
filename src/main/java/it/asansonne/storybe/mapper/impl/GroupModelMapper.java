package it.asansonne.storybe.mapper.impl;

import it.asansonne.storybe.dto.request.GroupRequest;
import it.asansonne.storybe.dto.response.GroupResponse;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.Group;
import org.springframework.stereotype.Component;

/**
 * The type Person mapper.
 */
@Component
public class GroupModelMapper implements RequestModelMapper<GroupRequest, Group>,
    ResponseModelMapper<Group, GroupResponse> {

  @Override
  public Group toModel(GroupRequest dto) {
    if (dto == null) {
      return null;
    }
    return Group.builder()
        .uuid(dto.getUuid())
        .build();
  }

  @Override
  public GroupResponse toDto(Group model) {
    if (model == null) {
      return null;
    }
    return GroupResponse.builder()
        .uuid(model.getUuid())
        .name(model.getName())
        .path(model.getPath())
        .build();
  }

  @Override
  public Group dtoToModelResponse(GroupResponse dto) {
    if (dto == null) {
      return null;
    }
    return Group.builder()
        .uuid(dto.getUuid())
        .name(dto.getName())
        .path(dto.getPath())
        .build();
  }

  /**
   * To request group request.
   *
   * @param group the group
   * @return the group request
   */
  public GroupRequest toRequest(Group group) {
    if (group == null) {
      return null;
    }
    return GroupRequest.builder()
        .uuid(group.getUuid())
        .build();
  }
}
