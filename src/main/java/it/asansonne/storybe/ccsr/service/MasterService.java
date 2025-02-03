package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.model.jpa.Master;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Master service.
 */
public interface MasterService {

  /**
   * Find master by email optional.
   *
   * @param email the email
   * @return the optional
   */
  Optional<Master> findMasterByEmail(String email);

  /**
   * Find master by uuid optional.
   *
   * @param masterUuid the master uuid
   * @return the optional
   */
  Optional<Master> findMasterByUuid(UUID masterUuid);

  /**
   * Find all masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Master> findAllMasters(Pageable pageable);

  /**
   * Find active masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Master> findActiveMasters(Pageable pageable);

  /**
   * Find inactive masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Master> findInactiveMasters(Pageable pageable);

  /**
   * Update master.
   *
   * @param master the master
   * @return the master
   */
  Master updateMaster(Master master);
}
