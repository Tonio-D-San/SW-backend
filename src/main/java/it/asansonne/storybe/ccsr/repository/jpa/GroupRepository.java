package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.GroupJpa;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Group repository.
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupJpa, Integer> {
  /**
   * Find group by uuid optional.
   *
   * @param groupUuid the group uuid
   * @return the optional
   */
  Optional<GroupJpa> findGroupByUuid(UUID groupUuid);
}
