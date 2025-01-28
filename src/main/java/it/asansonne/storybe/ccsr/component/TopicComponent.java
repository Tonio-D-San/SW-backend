package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.TopicRequest;
import it.asansonne.storybe.dto.request.TopicUpdateRequest;
import it.asansonne.storybe.dto.response.TopicResponse;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Topic component.
 */
public interface TopicComponent {

  /**
   * Find topic by uuid topic response.
   *
   * @param uuid the uuid
   * @return the topic response
   */
  TopicResponse findTopicByUuid(UUID uuid);

  /**
   * Find active topicJpas page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<TopicResponse> findActiveTopics(Pageable pageable);

  /**
   * Find inactive topicJpas page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<TopicResponse> findInactiveTopics(Pageable pageable);

  /**
   * Find all topicJpas by author page.
   *
   * @param pageable    the pageable
   * @param personEmail the person email
   * @return the page
   */
  Page<TopicResponse> findAllTopicsByAuthor(Pageable pageable, String personEmail);

  /**
   * Find topicJpas by title containing page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<TopicResponse> findTopicsByTitleContaining(String title, Pageable pageable);

  /**
   * Find all topicJpas sorted by field page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param field     the field
   * @return the page
   */
  Page<TopicResponse> findAllTopicsSortedByField(Integer page, Integer size, String direction,
                                                 String field);

  /**
   * Find last added topic response.
   *
   * @return the topic response
   */
  TopicResponse findLastAddedTopic();

  /**
   * Create topic response.
   *
   * @param topicRequest the topic request
   * @return the topic response
   */
  TopicResponse createTopic(Principal principal, TopicRequest topicRequest);

  /**
   * Update topic by uuid topic response.
   *
   * @param topicUpdateRequest the topic update request
   * @param uuidTopic          the uuid topic
   * @return the topic response
   * @throws Exception the exception
   */
  TopicResponse updateTopicByUuid(Principal principal, TopicUpdateRequest topicUpdateRequest,
                                  UUID uuidTopic) throws Exception;

  /**
   * Status topic by uuid.
   *
   * @param status    the status
   * @param uuid      the uuid
   */
  void statusTopicByUuid(Principal principal, StatusRequest status, UUID uuid);

  /**
   * Delete topic by uuid.
   *
   * @param uuid the uuid
   */
  void deleteTopicByUuid(UUID uuid);
}
