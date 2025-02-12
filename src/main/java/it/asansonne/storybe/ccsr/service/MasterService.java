package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.model.jpa.MasterJpa;
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
  Optional<MasterJpa> findMasterByEmail(String email);

  /**
   * Find master by uuid optional.
   *
   * @param masterUuid the master uuid
   * @return the optional
   */
  Optional<MasterJpa> findMasterByUuid(UUID masterUuid);

  /**
   * Find all masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterJpa> findAllMasters(Pageable pageable);

  /**
   * Find active masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterJpa> findActiveMasters(Pageable pageable);

  /**
   * Find inactive masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterJpa> findInactiveMasters(Pageable pageable);

  /**
   * Update master.
   *
   * @param master the master
   * @return the master
   */
  MasterJpa updateMaster(MasterJpa master);
}
