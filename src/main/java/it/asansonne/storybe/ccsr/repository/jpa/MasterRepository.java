package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.MasterJpa;
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
public interface MasterRepository extends JpaRepository<MasterJpa, Integer> {

  /**
   * Find master by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<MasterJpa> findMasterByUuid(UUID uuid);

  /**
   * Find by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<MasterJpa> findByEmail(String email);

  /**
   * Find all by is active true page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterJpa> findAllByIsActiveTrue(Pageable pageable);

  /**
   * Find all by is active false page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterJpa> findAllByIsActiveFalse(Pageable pageable);
}
