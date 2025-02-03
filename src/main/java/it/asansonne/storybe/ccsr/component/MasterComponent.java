package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.MasterGroupRequest;
import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.request.MasterUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.MasterResponse;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Master component.
 */
public interface MasterComponent {

  /**
   * Find master by uuid master response.
   *
   * @param masterUuid the master uuid
   * @return the master response
   */
  MasterResponse findMasterByUuid(UUID masterUuid);

  /**
   * Find all masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterResponse> findAllMasters(Pageable pageable);

  /**
   * Find active masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterResponse> findActiveMasters(Pageable pageable);

  /**
   * Find inactive masters page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<MasterResponse> findInactiveMasters(Pageable pageable);

  /**
   * Create master response.
   *
   * @param masterRequest the keycloak master request
   * @return the master response
   */
  MasterResponse createMaster(MasterRequest masterRequest);

  /**
   * Update master response.
   *
   * @param masterUpdateRequest the master request
   * @param masterUuid          the master uuid
   * @return the master response
   */
  MasterResponse updateMasterByUuid(Principal principal, MasterUpdateRequest masterUpdateRequest,
                                    UUID masterUuid);

  /**
   * Update master response.
   *
   * @param masterGroupRequest the master group request
   * @param masterUuid         the master uuid
   * @return the master response
   */
  MasterResponse updateGroupByMasterUuid(MasterGroupRequest masterGroupRequest,
                                         UUID masterUuid);

  /**
   * Update status master.
   *
   * @param masterUuid the master uuid
   * @param status     the master status
   */
  void updateStatusMasterByUuid(UUID masterUuid, StatusRequest status);
}
