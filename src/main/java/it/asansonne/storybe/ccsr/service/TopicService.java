package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.model.jpa.Person;
import it.asansonne.storybe.model.jpa.Topic;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Topic service.
 */
public interface TopicService {

  /**
   * Find topic by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<Topic> findTopicByUuid(UUID uuid);

  /**
   * Find active topics page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findActiveTopics(Pageable pageable);

  /**
   * Find inactive topics page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findInactiveTopics(Pageable pageable);

  /**
   * Find all topic by author page.
   *
   * @param author   the author
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findAllTopicByAuthor(Person author, Pageable pageable);

  /**
   * Find topics by title containing page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findTopicsByTitleContaining(String title, Pageable pageable);

  /**
   * Find last added topic.
   *
   * @return the topic
   */
  Topic findLastAddedTopic();

  /**
   * Create topic.
   *
   * @param Topic the topic
   * @return the topic
   */
  Topic createTopic(Topic Topic);

  /**
   * Update topic.
   *
   * @param Topic the topic
   * @return the topic
   */
  Topic updateTopic(Topic Topic);

  /**
   * Status topic by uuid.
   *
   * @param status the status
   * @param uuid   the uuid
   */
  void statusTopicByUuid(StatusRequest status, UUID uuid);

  /**
   * Delete topic by uuid.
   *
   * @param uuid the uuid
   */
  void deleteTopicByUuid(UUID uuid);
}
