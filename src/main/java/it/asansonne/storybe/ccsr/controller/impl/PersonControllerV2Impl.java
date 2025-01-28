package it.asansonne.storybe.ccsr.controller.impl;

import static it.asansonne.storybe.constant.SharedConstant.ADMIN_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.ADMIN_USER_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.USER_ROLES;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.asansonne.storybe.ccsr.component.PersonComponent;
import it.asansonne.storybe.ccsr.controller.PersonControllerV2;
import it.asansonne.storybe.dto.request.PersonGroupRequest;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.PersonUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The type Person controller v1.
 */
@RestController
@RequestMapping("api/v1/persons")
@AllArgsConstructor
@Tag(name = "PersonController V1")
@PreAuthorize(ADMIN_ROLES)
public class PersonControllerV2Impl implements PersonControllerV2 {
  private final PersonComponent personComponent;

  @Override
  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public PersonResponse findPersonByUuid(@PathVariable("uuid") UUID uuid) {
    return personComponent.findPersonByUuid(uuid);
  }

  @Override
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<PersonResponse> findAllPersons(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return personComponent.findAllPersons(pageRequest);
  }

  @Override
  @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_USER_ROLES)
  public Page<PersonResponse> findActivePersons(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return personComponent.findActivePersons(pageRequest);
  }

  @Override
  @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<PersonResponse> findInactivePersons(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return personComponent.findInactivePersons(pageRequest);
  }

  @Override
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<PersonResponse> createPerson(
      @Valid @RequestBody PersonRequest personRequest,
      UriComponentsBuilder builder) {
    PersonResponse response =
        personComponent.createPerson(personRequest);
    return ResponseEntity
        .created(builder
            .path("api/v2/admin/")
            .buildAndExpand(response)
            .toUri())
        .body(response);
  }

  @Override
  @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(USER_ROLES)
  public PersonResponse updatePersonByUuid(Principal principal,
                                           @Valid @RequestBody PersonUpdateRequest personRequest,
                                           @PathVariable("uuid") UUID uuid) {
    return personComponent.updatePersonByUuid(principal, personRequest, uuid);
  }

  @Override
  @PatchMapping(value = "/groups/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public PersonResponse updateGroupByPersonUuid(
      @Valid @RequestBody PersonGroupRequest personRequest,
      @PathVariable("uuid") UUID uuid) {
    return personComponent.updateGroupByPersonUuid(personRequest, uuid);
  }

  @Override
  @PatchMapping(value = "/status/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void updateStatusPersonByUuid(@PathVariable("uuid") UUID uuid,
                                       @RequestBody StatusRequest status) {
    personComponent.updateStatusPersonByUuid(uuid, status);
  }
}
