package it.asansonne.storybe.ccsr.service.impl;

import static it.asansonne.storybe.constant.MessageConstant.TOPIC_ACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.TOPIC_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.TOPIC_INACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.TOPIC_NOT_FOUND;

import it.asansonne.storybe.ccsr.repository.jpa.TopicRepository;
import it.asansonne.storybe.ccsr.service.TopicService;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.model.jpa.Person;
import it.asansonne.storybe.model.jpa.Topic;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Topic service.
 */
@Service
@AllArgsConstructor
public final class TopicServiceImpl implements TopicService {
  private final TopicRepository topicRepository;

  @Override
  public Optional<Topic> findTopicByUuid(UUID uuid) {
    Optional<Topic> topic = topicRepository.findTopicByUuid(uuid);
    if (topic.isEmpty()) {
      throw new EntityNotFoundException(TOPIC_EMPTY);
    }
    return topic;
  }

  @Override
  public Page<Topic> findActiveTopics(Pageable pageable) {
    Page<Topic> topics = topicRepository.findTopicsByIsActiveTrue(pageable);
    if (topics.isEmpty()) {
      throw new EntityNotFoundException(TOPIC_ACTIVE_EMPTY);
    }
    return topics;
  }

  @Override
  public Page<Topic> findInactiveTopics(Pageable pageable) {
    Page<Topic> topics = topicRepository.findTopicsByIsActiveFalse(pageable);
    if (topics.isEmpty()) {
      throw new EntityNotFoundException(TOPIC_INACTIVE_EMPTY);
    }
    return topics;
  }

  @Override
  public Page<Topic> findAllTopicByAuthor(Person author, Pageable pageable) {
    Page<Topic> topics = topicRepository.findAllTopicsByAuthor(author, pageable);
    if (topics.isEmpty()) {
      throw new EntityNotFoundException(TOPIC_EMPTY);
    }
    return topics;
  }

  @Override
  public Page<Topic> findTopicsByTitleContaining(String title, Pageable pageable) {
    Page<Topic> topics =
        topicRepository.findTopicsByTitleContainingIgnoreCase(title.trim(), pageable);
    if (topics.isEmpty()) {
      throw new EntityNotFoundException(TOPIC_EMPTY);
    }
    return topics;
  }

  @Override
  public Topic findLastAddedTopic() {
    Topic topic = topicRepository.findLastAddedTopic();
    if (topic == null) {
      throw new EntityNotFoundException(TOPIC_NOT_FOUND);
    }
    return topic;
  }

  @Override
  public Topic createTopic(Topic topic) {
    return topicRepository.save(topic);
  }

  @Override
  public Topic updateTopic(Topic topic) {
    return topicRepository.save(topic);
  }

  @Override
  public void statusTopicByUuid(StatusRequest status, UUID uuid) {
    Topic topic = findTopic(uuid);
    topic.setIsActive(status.getIsActive());
    topicRepository.save(topic);
  }

  @Override
  public void deleteTopicByUuid(UUID uuid) {
    topicRepository.delete(findTopic(uuid));
  }

  private Topic findTopic(UUID uuid) {
    return topicRepository.findTopicByUuid(uuid)
        .orElseThrow(() -> new NotFoundException(TOPIC_NOT_FOUND));
  }
}
