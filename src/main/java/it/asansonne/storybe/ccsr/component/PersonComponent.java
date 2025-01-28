package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.PersonGroupRequest;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.PersonUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Person component.
 */
public interface PersonComponent {

  /**
   * Find person by uuid person response.
   *
   * @param personUuid the person uuid
   * @return the person response
   */
  PersonResponse findPersonByUuid(UUID personUuid);

  /**
   * Find all persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<PersonResponse> findAllPersons(Pageable pageable);

  /**
   * Find active persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<PersonResponse> findActivePersons(Pageable pageable);

  /**
   * Find inactive persons page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<PersonResponse> findInactivePersons(Pageable pageable);

  /**
   * Create person response.
   *
   * @param personRequest the keycloak person request
   * @return the person response
   */
  PersonResponse createPerson(PersonRequest personRequest);

  /**
   * Update person response.
   *
   * @param personUpdateRequest the person request
   * @param personUuid          the person uuid
   * @return the person response
   */
  PersonResponse updatePersonByUuid(Principal principal, PersonUpdateRequest personUpdateRequest,
                                    UUID personUuid);

  /**
   * Update person response.
   *
   * @param personGroupRequest the person group request
   * @param personUuid         the person uuid
   * @return the person response
   */
  PersonResponse updateGroupByPersonUuid(PersonGroupRequest personGroupRequest,
                                         UUID personUuid);

  /**
   * Update status person.
   *
   * @param personUuid the person uuid
   * @param status     the person status
   */
  void updateStatusPersonByUuid(UUID personUuid, StatusRequest status);
}
