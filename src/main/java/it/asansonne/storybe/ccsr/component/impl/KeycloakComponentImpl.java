package it.asansonne.storybe.ccsr.component.impl;

import static it.asansonne.storybe.constant.MessageConstant.GROUP_NOT_FOUND;
import static it.asansonne.storybe.constant.MessageConstant.JWT_ERROR;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_NOT_FOUND;

import it.asansonne.storybe.ccsr.component.KeycloakComponent;
import it.asansonne.storybe.ccsr.service.GroupService;
import it.asansonne.storybe.dto.request.GroupRequest;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.mapper.impl.PersonModelMapper;
import it.asansonne.storybe.model.jpa.Group;
import it.asansonne.storybe.model.jpa.Person;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The type Keycloak component.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakComponentImpl implements KeycloakComponent {
  private final PersonModelMapper personMapper;
  private final ResponseModelMapper<Person, PersonResponse> responseModelMapper;
  private final GroupService groupService;
  @Value("${keycloak.host.user}")
  private String urlUser;

  /**
   * Read user from Keycloak by username.
   *
   * @param email of the user
   */
  public Person readUser(String email) {
    if (SecurityContextHolder.getContext().getAuthentication()
        instanceof JwtAuthenticationToken jwtAuthToken) {
      ResponseEntity<String> response = new RestTemplate()
          .exchange(urlUser + "?email=" + email, HttpMethod.GET,
              new HttpEntity<>(setHeader(jwtAuthToken)), String.class);
      if (response.getStatusCode() != HttpStatus.OK) {
        throw new NotFoundException(PERSON_NOT_FOUND);
      }
      return responseModelMapper.dtoToModelResponse(
          personMapper.jsonToDto(response.getBody()).get(0)
      );
    }

    throw new IllegalStateException(JWT_ERROR);
  }

  /**
   * Create user person keycloak response.
   *
   * @param request of the person
   */
  public void createUser(PersonRequest request) {
    if (SecurityContextHolder.getContext().getAuthentication()
        instanceof JwtAuthenticationToken jwtAuthToken) {
      new RestTemplate()
          .exchange(urlUser, HttpMethod.POST,
              new HttpEntity<>(setPayload(request), setHeader(jwtAuthToken)), String.class);
    } else {
      throw new IllegalStateException(JWT_ERROR);
    }
  }

  /**
   * Update user in Keycloak.
   *
   * @param userUuid the user id
   * @param request  updated user data
   */
  public void updateUser(UUID userUuid, Group request) {
    if (SecurityContextHolder.getContext().getAuthentication()
        instanceof JwtAuthenticationToken jwtAuthToken) {
      new RestTemplate()
          .exchange(urlUser + "/" + userUuid.toString()
                  + "/groups/"
                  + request.getUuid(), HttpMethod.PUT,
              new HttpEntity<>("{}", setHeader(jwtAuthToken)), String.class);
    } else {
      throw new IllegalStateException(JWT_ERROR);
    }
  }

  /**
   * Update status user in Keycloak.
   *
   * @param userUuid the user id
   * @param status   updated user status
   */
  public void updateStatusUser(UUID userUuid, StatusRequest status) {
    if (SecurityContextHolder.getContext().getAuthentication()
        instanceof JwtAuthenticationToken jwtAuthToken) {
      new RestTemplate()
          .exchange(urlUser + "/" + userUuid.toString(), HttpMethod.PUT,
              new HttpEntity<>(setPayload(status), setHeader(jwtAuthToken)), String.class);
    } else {
      throw new IllegalStateException(JWT_ERROR);
    }
  }

  @Override
  public void deleteUserGroup(UUID userUuid, Group group) {
    if (SecurityContextHolder.getContext().getAuthentication()
        instanceof JwtAuthenticationToken jwtAuthToken) {
      new RestTemplate()
          .exchange(urlUser + "/" + userUuid.toString() + "/groups/"
                  + group.getUuid(), HttpMethod.DELETE,
              new HttpEntity<>("{}", setHeader(jwtAuthToken)), String.class);
    } else {
      throw new IllegalStateException(JWT_ERROR);
    }
  }

  private HttpHeaders setHeader(JwtAuthenticationToken jwtAuthToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + jwtAuthToken.getToken().getTokenValue());
    headers.set("Content-Type", "application/json");
    return headers;
  }

  private String setPayload(PersonRequest request) {
    if (request.getGroups() == null || request.getGroups().isEmpty()) {
      return "{"
          + "\"username\": \"" + request.getUsername() + "\","
          + "\"credentials\": [{"
          + "    \"type\": \"password\","
          + "    \"value\": \"" + request.getPassword() + "\","
          + "    \"temporary\": true"
          + "}],"
          + "\"requiredActions\": [\"UPDATE_PASSWORD\"],"
          + "\"email\": \"" + request.getEmail() + "\","
          + "\"lastName\": \"" + request.getLastname() + "\","
          + "\"firstName\": \"" + request.getFirstname() + "\","
          + "\"enabled\": " + request.getIsActive()
          + "}";
    } else {
      return "{"
          + "\"username\": \"" + request.getUsername() + "\","
          + "\"credentials\": [{"
          + "    \"type\": \"password\","
          + "    \"value\": \"" + request.getPassword() + "\","
          + "    \"temporary\": true"
          + "}],"
          + "\"requiredActions\": [\"UPDATE_PASSWORD\"],"
          + "\"email\": \"" + request.getEmail() + "\","
          + "\"lastName\": \"" + request.getLastname() + "\","
          + "\"firstName\": \"" + request.getFirstname() + "\","
          + "\"groups\": " + groupJson(request.getGroups()) + ","
          + "\"enabled\": " + request.getIsActive()
          + "}";
    }
  }

  private String setPayload(StatusRequest status) {
    return "{"
        + "\"enabled\": " + status.getIsActive()
        + "}";
  }

  private StringBuilder groupJson(List<GroupRequest> groups) {
    StringBuilder groupsJson = new StringBuilder("[");
    for (GroupRequest group : groups) {
      if (groupsJson.length() > 1) {
        groupsJson.append(",");
      }
      groupsJson.append("\"")
          .append(groupService.findGroupByUuid(group.getUuid())
              .orElseThrow(() -> new NotFoundException(GROUP_NOT_FOUND))
              .getPath())
          .append("\"");
    }
    groupsJson.append("]");
    return groupsJson;
  }
}
