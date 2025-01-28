package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.model.jpa.Group;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Group service.
 */
public interface GroupService {

  /**
   * Find group by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<Group> findGroupByUuid(UUID uuid);
}
