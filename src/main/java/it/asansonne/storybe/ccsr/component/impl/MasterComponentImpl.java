package it.asansonne.storybe.ccsr.component.impl;

import static it.asansonne.storybe.constant.MessageConstant.FORBIDDEN;
import static it.asansonne.storybe.constant.MessageConstant.GROUP_NOT_FOUND;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_NOT_FOUND;
import static it.asansonne.storybe.constant.SharedConstant.DEFAULT_GROUP;

import it.asansonne.storybe.ccsr.component.KeycloakComponent;
import it.asansonne.storybe.ccsr.component.MasterComponent;
import it.asansonne.storybe.ccsr.service.GroupService;
import it.asansonne.storybe.ccsr.service.MasterService;
import it.asansonne.storybe.dto.request.GroupRequest;
import it.asansonne.storybe.dto.request.MasterGroupRequest;
import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.request.MasterUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.MasterResponse;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.mapper.impl.GroupModelMapper;
import it.asansonne.storybe.model.jpa.Group;
import it.asansonne.storybe.model.jpa.Master;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * The type Master component.
 */
@Component
@AllArgsConstructor
public class MasterComponentImpl implements MasterComponent {
  private final KeycloakComponent keycloakComponent;
  private final MasterService masterService;
  private final GroupService groupService;
  private final ResponseModelMapper<Master, MasterResponse> masterResponseModelMapper;
  private final GroupModelMapper groupMapper;

  @Override
  public MasterResponse findMasterByUuid(UUID masterUuid) {
    Master master = findMaster(masterUuid);
    Master userResponse = keycloakComponent.readUser(master.getEmail());
    userResponse.setGroups(master.getGroups());
    userResponse.setBiography(master.getBiography());
    userResponse.setProfileImage(master.getProfileImage());
    return masterResponseModelMapper.toDto(userResponse);
  }

  @Override
  public Page<MasterResponse> findAllMasters(Pageable pageable) {
    return masterResponseModelMapper.toDto(masterService.findAllMasters(pageable), pageable);
  }

  @Override
  public Page<MasterResponse> findActiveMasters(Pageable pageable) {
    return masterResponseModelMapper.toDto(masterService.findActiveMasters(pageable), pageable);
  }

  @Override
  public Page<MasterResponse> findInactiveMasters(Pageable pageable) {
    return masterResponseModelMapper.toDto(masterService.findInactiveMasters(pageable), pageable);
  }

  @Override
  public MasterResponse createMaster(MasterRequest masterRequest) {
    if (masterRequest.getGroups() == null || masterRequest.getGroups().isEmpty()) {
      masterRequest.setGroups(List.of(groupToGroupRequest()));
    }
    keycloakComponent.createUser(masterRequest);
    Master master = keycloakComponent.readUser(masterRequest.getEmail());
    List<Group> groups = listGroups(masterRequest.getGroups());
    master.setGroups(groups);
    for (Group group : groups) {
      group.getMasters().add(master);
    }
    master.setBiography(masterRequest.getBiography());
    master.setProfileImage(masterRequest.getProfileImage());
    return masterResponseModelMapper.toDto(masterService.updateMaster(master));
  }

  // masterService.updateMaster is a method that saves the master
  @Override
  public MasterResponse updateMasterByUuid(Principal principal,
                                           MasterUpdateRequest masterUpdateRequest,
                                           UUID masterUuid) {
    if (findMaster(UUID.fromString(principal.getName().split("[,\\[\\]\\s]+")[1])).getUuid()
        .equals(masterUuid)) {
      return masterResponseModelMapper.toDto(
          masterService.updateMaster(updateFields(masterUpdateRequest, findMaster(masterUuid)))
      );
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  // masterService.updateGroupsMaster is a method that saves the master
  @Override
  public MasterResponse updateGroupByMasterUuid(MasterGroupRequest masterUpdateRequest,
                                                UUID masterUuid) {
    Master master = findMaster(masterUuid);
    makeGroup(master, masterUpdateRequest);
    return masterResponseModelMapper.toDto(
        masterService.updateMaster(master)
    );
  }

  @Override
  public void updateStatusMasterByUuid(UUID masterUuid, StatusRequest status) {
    keycloakComponent.updateStatusUser(masterUuid, status);
    Master master = findMaster(masterUuid);
    master.setIsActive(status.getIsActive());
    masterService.updateMaster(master);
  }

  private Master findMaster(UUID masterUuid) {
    return masterService.findMasterByUuid(masterUuid)
        .orElseThrow(() -> new NotFoundException(PERSON_NOT_FOUND));
  }

  private void makeGroup(Master master, MasterGroupRequest masterUpdateRequest) {
    List<Group> currentGroups = new ArrayList<>(master.getGroups());
    List<Group> newGroups = listGroups(masterUpdateRequest.getGroups());
    removeUser(currentGroups, newGroups, master);
    addUser(currentGroups, newGroups, master);
  }

  // I take the user's groups and the groups from the DTO,
  // check if in the request I don't have the group then it means I'm no longer part of it,
  // and it deletes it from both the entity list and keycloak
  private void removeUser(List<Group> currentGroups, List<Group> newGroups, Master master) {
    for (Group currentGroup : currentGroups) {
      if (!getGroupUuidFromGroups(newGroups).contains(currentGroup.getUuid())) {
        keycloakComponent.deleteUserGroup(master.getUuid(), currentGroup);
        master.getGroups().remove(currentGroup);
      }
    }
  }

  // I take the user's groups and groups from the DTO, check if in the request
  // I have the group then I don't do anything, otherwise, if I don't have it,
  // it adds it either on the entity list or on keycloak
  private void addUser(List<Group> currentGroups, List<Group> newGroups, Master master) {
    for (Group newGroup : newGroups) {
      if (!getGroupUuidFromGroups(currentGroups).contains(newGroup.getUuid())) {
        keycloakComponent.updateUser(master.getUuid(), newGroup);
        master.getGroups().add(newGroup);
      }
    }
  }

  private Set<UUID> getGroupUuidFromGroups(List<Group> group) {
    return group.stream()
        .map(Group::getUuid)
        .collect(Collectors.toSet());
  }

  private GroupRequest groupToGroupRequest() {
    return groupMapper.toRequest(groupService.findGroupByUuid(DEFAULT_GROUP)
        .orElseThrow(() -> new NotFoundException(GROUP_NOT_FOUND)));
  }

  private List<Group> listGroups(List<GroupRequest> groups) {
    return groups.stream().map(
            group -> groupService.findGroupByUuid(group.getUuid())
                .orElseThrow(() -> new NotFoundException(GROUP_NOT_FOUND)))
        .collect(Collectors.toList());
  }

  private Master updateFields(MasterUpdateRequest request, Master master) {
    master.setBiography(
        request.getBiography() != null ? request.getBiography() : master.getBiography());
    master.setProfileImage(
        request.getProfileImage() != null ? request.getProfileImage() : master.getProfileImage());
    return master;
  }
}
