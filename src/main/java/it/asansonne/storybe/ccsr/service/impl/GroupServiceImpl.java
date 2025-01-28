package it.asansonne.storybe.ccsr.service.impl;

import static it.asansonne.storybe.constant.MessageConstant.GROUP_EMPTY;

import it.asansonne.storybe.ccsr.repository.jpa.GroupRepository;
import it.asansonne.storybe.ccsr.service.GroupService;
import it.asansonne.storybe.model.jpa.Group;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type Group service.
 */
@Service
@AllArgsConstructor
public final class GroupServiceImpl implements GroupService {
  private final GroupRepository groupRepository;

  @Override
  public Optional<Group> findGroupByUuid(UUID uuid) {
    Optional<Group> group = groupRepository.findGroupByUuid(uuid);
    if (group.isEmpty()) {
      throw new EntityNotFoundException(GROUP_EMPTY);
    }
    return group;
  }
}
