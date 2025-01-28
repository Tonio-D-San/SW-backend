package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.Person;
import it.asansonne.storybe.model.jpa.Topic;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Topic repository.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  /**
   * Find all topics by author page.
   *
   * @param author   the author
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findAllTopicsByAuthor(Person author, Pageable pageable);

  /**
   * Find last added topic.
   *
   * @return the topic
   */
  @Query(value = "SELECT t.* FROM topic t ORDER BY date_creation DESC LIMIT 1", nativeQuery = true)
  Topic findLastAddedTopic();

  /**
   * Find topics by title containing ignore case page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findTopicsByTitleContainingIgnoreCase(String title, Pageable pageable);

  /**
   * Find topic by uuid optional.
   *
   * @param topicUuid the topic uuid
   * @return the optional
   */
  Optional<Topic> findTopicByUuid(UUID topicUuid);

  /**
   * Find topics by is active true page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findTopicsByIsActiveTrue(Pageable pageable);

  /**
   * Find topics by is active false page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<Topic> findTopicsByIsActiveFalse(Pageable pageable);
}