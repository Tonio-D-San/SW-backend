package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.Master;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Master repository.
 */
@Repository
public interface MasterRepository extends JpaRepository<Master, Integer> {

  /**
   * Find master by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<Master> findMasterByUuid(UUID uuid);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<Master> findByEmail(String email);

  /**
   * Find all by is active true page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Master> findAllByIsActiveTrue(Pageable pageable);

  /**
   * Find all by is active false page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Master> findAllByIsActiveFalse(Pageable pageable);
}
