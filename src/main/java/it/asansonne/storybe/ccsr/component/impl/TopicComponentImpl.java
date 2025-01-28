package it.asansonne.storybe.ccsr.component.impl;

import static it.asansonne.storybe.constant.MessageConstant.FORBIDDEN;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_NOT_FOUND;
import static it.asansonne.storybe.constant.MessageConstant.TOPIC_NOT_FOUND;
import static it.asansonne.storybe.util.FileUtil.isAdmin;

import it.asansonne.storybe.ccsr.component.TopicComponent;
import it.asansonne.storybe.ccsr.service.PersonService;
import it.asansonne.storybe.ccsr.service.TopicService;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.TopicRequest;
import it.asansonne.storybe.dto.request.TopicUpdateRequest;
import it.asansonne.storybe.dto.response.TopicResponse;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.Person;
import it.asansonne.storybe.model.jpa.Topic;
import jakarta.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

/**
 * The type Topic component.
 */
@Component
@AllArgsConstructor
public class TopicComponentImpl implements TopicComponent {
  private final TopicService topicService;
  private final PersonService personService;
  private final ResponseModelMapper<Topic, TopicResponse> responseModelMapper;
  private final RequestModelMapper<TopicRequest, Topic> requestModelMapper;


  @Override
  public TopicResponse findTopicByUuid(UUID uuid) {
    return responseModelMapper.toDto(topicService.findTopicByUuid(uuid)
        .orElseThrow(() -> new EntityNotFoundException(TOPIC_NOT_FOUND)));
  }

  @Override
  public Page<TopicResponse> findActiveTopics(Pageable pageable) {
    return responseModelMapper.toDto(topicService.findActiveTopics(pageable), pageable);
  }

  @Override
  public Page<TopicResponse> findInactiveTopics(Pageable pageable) {
    return responseModelMapper.toDto(topicService.findInactiveTopics(pageable), pageable);
  }

  @Override
  public Page<TopicResponse> findAllTopicsByAuthor(Pageable pageable, String personEmail) {
    Person person = personService.findPersonByEmail(personEmail)
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
    Page<Topic> topics = topicService.findAllTopicByAuthor(person, pageable);
    return responseModelMapper.toDto(topics, pageable);
  }

  @Override
  public Page<TopicResponse> findTopicsByTitleContaining(String title, Pageable pageable) {
    return responseModelMapper.toDto(topicService.findTopicsByTitleContaining(title, pageable),
        pageable);
  }

  @Override
  public Page<TopicResponse> findAllTopicsSortedByField(Integer page, Integer size,
                                                        String direction, String field) {
    PageRequest pageable = sorting(page, size, direction, field);
    return responseModelMapper.toDto(topicService.findActiveTopics(pageable), pageable);
  }

  @Override
  public TopicResponse findLastAddedTopic() {
    return responseModelMapper.toDto(topicService.findLastAddedTopic());
  }

  @Override
  public TopicResponse createTopic(Principal principal, TopicRequest topicRequest) {
    Topic topic = requestModelMapper.toModel(topicRequest);
    topic.setAuthor(findPerson(principal));
    topic.setUuid(UUID.randomUUID());
    long creationDate = Instant.now().toEpochMilli();
    topic.setCreationDate(creationDate);
    topic.setLastEditDate(creationDate);
    topic.setIsActive(true);
    return responseModelMapper.toDto(topicService.createTopic(topic));
  }

  @Override
  public TopicResponse updateTopicByUuid(Principal principal, TopicUpdateRequest topicUpdateRequest,
                                         UUID uuidTopic) {
    Topic topic = topicService.findTopicByUuid(uuidTopic)
        .orElseThrow(() -> new NotFoundException(TOPIC_NOT_FOUND));
    if (findPerson(principal).getUuid().equals(topic.getAuthor().getUuid())) {
      topic.setTitle(topicUpdateRequest.getTitle());
      topic.setSolution(topicUpdateRequest.getSolution());
      topic.setIsOpen(topicUpdateRequest.getSolution() == null);
      topic.setProblem(topicUpdateRequest.getProblem());
      return responseModelMapper.toDto(topicService.updateTopic(topic));
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  @Override
  public void statusTopicByUuid(Principal principal, StatusRequest status, UUID topicUuid) {
    Topic topic = topicService.findTopicByUuid(topicUuid)
        .orElseThrow(() -> new NotFoundException(TOPIC_NOT_FOUND));
    Person person = findPerson(principal);
    if (isAdmin(person) || status.getIsActive().equals(false)
        && (person.getUuid().equals(topic.getAuthor().getUuid()))) {
      topicService.statusTopicByUuid(status, topicUuid);
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  @Override
  public void deleteTopicByUuid(UUID uuid) {
    topicService.deleteTopicByUuid(uuid);
  }

  private PageRequest sorting(Integer page, Integer size, String direction, String field) {
    Sort sort;
    String author = "author.surname";
    String category = "categories.name";
    String title = "title";
    String creationDate = "creationDate";
    Sort sortDate = Sort.by(Sort.Direction.DESC, creationDate);
    Sort sortTitle = Sort.by(Sort.Direction.DESC, title);
    sort = switch (field) {
      case "author" -> Sort.by(Sort.Direction.fromString(direction), author).and(sortDate);
      case "category" -> Sort.by(Sort.Direction.fromString(direction), category).and(sortDate);
      case "title" -> Sort.by(Sort.Direction.fromString(direction), title).and(sortDate);
      case "creationDate" ->
          Sort.by(Sort.Direction.fromString(direction), creationDate).and(sortTitle);
      default -> throw new EntityNotFoundException("Field not found");
    };
    return PageRequest.of(page, size, sort);
  }

  private Person findPerson(Principal principal) {
    return personService.findPersonByUuid(
            UUID.fromString(principal.getName().split("[,\\[\\]\\s]+")[1]))
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
  }
}
