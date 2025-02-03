package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.model.jpa.Group;
import it.asansonne.storybe.model.jpa.Master;
import java.util.UUID;

/**
 * The interface Keycloak component.
 */
public interface KeycloakComponent {

  /**
   * Read user master.
   *
   * @param email the email
   * @return the master
   */
  Master readUser(String email);

  /**
   * Create user.
   *
   * @param request the request
   */
  void createUser(MasterRequest request);

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
