package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.Group;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Group repository.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
  /**
   * Find group by uuid optional.
   *
   * @param groupUuid the group uuid
   * @return the optional
   */
  Optional<Group> findGroupByUuid(UUID groupUuid);
}
