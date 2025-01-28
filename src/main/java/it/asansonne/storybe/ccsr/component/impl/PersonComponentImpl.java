package it.asansonne.storybe.ccsr.component.impl;

import static it.asansonne.storybe.constant.MessageConstant.FORBIDDEN;
import static it.asansonne.storybe.constant.MessageConstant.GROUP_NOT_FOUND;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_NOT_FOUND;
import static it.asansonne.storybe.constant.SharedConstant.DEFAULT_GROUP;

import it.asansonne.storybe.ccsr.component.KeycloakComponent;
import it.asansonne.storybe.ccsr.component.PersonComponent;
import it.asansonne.storybe.ccsr.service.GroupService;
import it.asansonne.storybe.ccsr.service.PersonService;
import it.asansonne.storybe.dto.request.GroupRequest;
import it.asansonne.storybe.dto.request.PersonGroupRequest;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.PersonUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.mapper.impl.GroupModelMapper;
import it.asansonne.storybe.model.jpa.Group;
import it.asansonne.storybe.model.jpa.Person;
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
 * The type Person component.
 */
@Component
@AllArgsConstructor
public class PersonComponentImpl implements PersonComponent {
  private final KeycloakComponent keycloakComponent;
  private final PersonService personService;
  private final GroupService groupService;
  private final ResponseModelMapper<Person, PersonResponse> personResponseModelMapper;
  private final GroupModelMapper groupMapper;

  @Override
  public PersonResponse findPersonByUuid(UUID personUuid) {
    Person person = findPerson(personUuid);
    Person userResponse = keycloakComponent.readUser(person.getEmail());
    userResponse.setGroups(person.getGroups());
    userResponse.setBiography(person.getBiography());
    userResponse.setProfileImage(person.getProfileImage());
    return personResponseModelMapper.toDto(userResponse);
  }

  @Override
  public Page<PersonResponse> findAllPersons(Pageable pageable) {
    return personResponseModelMapper.toDto(personService.findAllPersons(pageable), pageable);
  }

  @Override
  public Page<PersonResponse> findActivePersons(Pageable pageable) {
    return personResponseModelMapper.toDto(personService.findActivePersons(pageable), pageable);
  }

  @Override
  public Page<PersonResponse> findInactivePersons(Pageable pageable) {
    return personResponseModelMapper.toDto(personService.findInactivePersons(pageable), pageable);
  }

  @Override
  public PersonResponse createPerson(PersonRequest personRequest) {
    if (personRequest.getGroups() == null || personRequest.getGroups().isEmpty()) {
      personRequest.setGroups(List.of(groupToGroupRequest()));
    }
    keycloakComponent.createUser(personRequest);
    Person person = keycloakComponent.readUser(personRequest.getEmail());
    List<Group> groups = listGroups(personRequest.getGroups());
    person.setGroups(groups);
    for (Group group : groups) {
      group.getPersons().add(person);
    }
    person.setBiography(personRequest.getBiography());
    person.setProfileImage(personRequest.getProfileImage());
    return personResponseModelMapper.toDto(personService.updatePerson(person));
  }

  // personService.updatePerson is a method that saves the person
  @Override
  public PersonResponse updatePersonByUuid(Principal principal,
                                           PersonUpdateRequest personUpdateRequest,
                                           UUID personUuid) {
    if (findPerson(UUID.fromString(principal.getName().split("[,\\[\\]\\s]+")[1])).getUuid()
        .equals(personUuid)) {
      return personResponseModelMapper.toDto(
          personService.updatePerson(updateFields(personUpdateRequest, findPerson(personUuid)))
      );
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  // personService.updateGroupsPerson is a method that saves the person
  @Override
  public PersonResponse updateGroupByPersonUuid(PersonGroupRequest personUpdateRequest,
                                                UUID personUuid) {
    Person person = findPerson(personUuid);
    makeGroup(person, personUpdateRequest);
    return personResponseModelMapper.toDto(
        personService.updatePerson(person)
    );
  }

  @Override
  public void updateStatusPersonByUuid(UUID personUuid, StatusRequest status) {
    keycloakComponent.updateStatusUser(personUuid, status);
    Person person = findPerson(personUuid);
    person.setIsActive(status.getIsActive());
    personService.updatePerson(person);
  }

  private Person findPerson(UUID personUuid) {
    return personService.findPersonByUuid(personUuid)
        .orElseThrow(() -> new NotFoundException(PERSON_NOT_FOUND));
  }

  private void makeGroup(Person person, PersonGroupRequest personUpdateRequest) {
    List<Group> currentGroups = new ArrayList<>(person.getGroups());
    List<Group> newGroups = listGroups(personUpdateRequest.getGroups());
    removeUser(currentGroups, newGroups, person);
    addUser(currentGroups, newGroups, person);
  }

  // I take the user's groups and the groups from the DTO,
  // check if in the request I don't have the group then it means I'm no longer part of it,
  // and it deletes it from both the entity list and keycloak
  private void removeUser(List<Group> currentGroups, List<Group> newGroups, Person person) {
    for (Group currentGroup : currentGroups) {
      if (!getGroupUuidFromGroups(newGroups).contains(currentGroup.getUuid())) {
        keycloakComponent.deleteUserGroup(person.getUuid(), currentGroup);
        person.getGroups().remove(currentGroup);
      }
    }
  }

  // I take the user's groups and groups from the DTO, check if in the request
  // I have the group then I don't do anything, otherwise, if I don't have it,
  // it adds it either on the entity list or on keycloak
  private void addUser(List<Group> currentGroups, List<Group> newGroups, Person person) {
    for (Group newGroup : newGroups) {
      if (!getGroupUuidFromGroups(currentGroups).contains(newGroup.getUuid())) {
        keycloakComponent.updateUser(person.getUuid(), newGroup);
        person.getGroups().add(newGroup);
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

  private Person updateFields(PersonUpdateRequest request, Person person) {
    person.setBiography(
        request.getBiography() != null ? request.getBiography() : person.getBiography());
    person.setProfileImage(
        request.getProfileImage() != null ? request.getProfileImage() : person.getProfileImage());
    return person;
  }
}
