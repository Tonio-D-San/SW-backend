package it.asansonne.storybe.ccsr.repository.jpa;

import it.asansonne.storybe.model.jpa.MasterJpa;
import it.asansonne.storybe.model.jpa.StoryJpa;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Story repository.
 */
@Repository
public interface StoryRepository extends JpaRepository<StoryJpa, Long> {

  /**
   * Find all stories by author page.
   *
   * @param author   the author
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findAllStoriesByAuthor(MasterJpa author, Pageable pageable);

  /**
   * Find last added story.
   *
   * @return the story
   */
  @Query(value = "SELECT t.* FROM story t ORDER BY date_creation DESC LIMIT 1", nativeQuery = true)
  StoryJpa findLastAddedStory();

  /**
   * Find stories by title containing ignore case page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findStoriesByTitleContainingIgnoreCase(String title, Pageable pageable);

  /**
   * Find story by uuid optional.
   *
   * @param storyUuid the story uuid
   * @return the optional
   */
  Optional<StoryJpa> findStoryByUuid(UUID storyUuid);

  /**
   * Find stories by is active true page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findStoriesByIsActiveTrue(Pageable pageable);

  /**
   * Find stories by is active false page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findStoriesByIsActiveFalse(Pageable pageable);
}