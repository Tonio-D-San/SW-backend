package it.asansonne.storybe.ccsr.controller.impl;

import static it.asansonne.storybe.constant.SharedConstant.ADMIN_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.ADMIN_USER_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.USER_ROLES;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.asansonne.storybe.ccsr.component.MasterComponent;
import it.asansonne.storybe.ccsr.controller.MasterControllerV1;
import it.asansonne.storybe.dto.request.MasterGroupRequest;
import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.request.MasterUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.MasterResponse;
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
 * The type Master controller v1.
 */
@RestController
@RequestMapping("api/v1/masters")
@AllArgsConstructor
@Tag(name = "MasterController V1")
@PreAuthorize(ADMIN_ROLES)
public class MasterControllerV1Impl implements MasterControllerV1 {
  private final MasterComponent masterComponent;

  @Override
  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public MasterResponse findMasterByUuid(@PathVariable("uuid") UUID uuid) {
    return masterComponent.findMasterByUuid(uuid);
  }

  @Override
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<MasterResponse> findAllMasters(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return masterComponent.findAllMasters(pageRequest);
  }

  @Override
  @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_USER_ROLES)
  public Page<MasterResponse> findActiveMasters(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return masterComponent.findActiveMasters(pageRequest);
  }

  @Override
  @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<MasterResponse> findInactiveMasters(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), SURNAME));
    return masterComponent.findInactiveMasters(pageRequest);
  }

  @Override
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<MasterResponse> createMaster(
      @Valid @RequestBody MasterRequest masterRequest,
      UriComponentsBuilder builder) {
    MasterResponse response =
        masterComponent.createMaster(masterRequest);
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
  public MasterResponse updateMasterByUuid(Principal principal,
                                           @Valid @RequestBody MasterUpdateRequest masterRequest,
                                           @PathVariable("uuid") UUID uuid) {
    return masterComponent.updateMasterByUuid(principal, masterRequest, uuid);
  }

  @Override
  @PatchMapping(value = "/groups/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public MasterResponse updateGroupByMasterUuid(
      @Valid @RequestBody MasterGroupRequest masterRequest,
      @PathVariable("uuid") UUID uuid) {
    return masterComponent.updateGroupByMasterUuid(masterRequest, uuid);
  }

  @Override
  @PatchMapping(value = "/status/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public void updateStatusMasterByUuid(@PathVariable("uuid") UUID uuid,
                                       @RequestBody StatusRequest status) {
    masterComponent.updateStatusMasterByUuid(uuid, status);
  }
}
