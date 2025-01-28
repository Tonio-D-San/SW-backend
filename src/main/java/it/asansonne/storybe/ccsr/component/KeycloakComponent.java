package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.model.jpa.Group;
import it.asansonne.storybe.model.jpa.Person;
import java.util.UUID;

/**
 * The interface Keycloak component.
 */
public interface KeycloakComponent {

  /**
   * Read user person.
   *
   * @param email the email
   * @return the person
   */
  Person readUser(String email);

  /**
   * Create user.
   *
   * @param request the request
   */
  void createUser(PersonRequest request);

  /**
   * Update user.
   *
   * @param userUuid the user uuid
   * @param request  the request
   */
  void updateUser(UUID userUuid, Group request);

  /**
   * Update status user.
   *
   * @param userUuid the user uuid
   * @param status   the status
   */
  void updateStatusUser(UUID userUuid, StatusRequest status);

  /**
   * Delete user group.
   *
   * @param userUuid the user uuid
   * @param group    the group
   */
  void deleteUserGroup(UUID userUuid, Group group);
}
