package it.asansonne.storybe.ccsr.service;

import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.model.jpa.Master;
import it.asansonne.storybe.model.jpa.StoryJpa;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Story service.
 */
public interface StoryService {

  /**
   * Find story by uuid optional.
   *
   * @param uuid the uuid
   * @return the optional
   */
  Optional<StoryJpa> findStoryByUuid(UUID uuid);

  /**
   * Find active stories page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findActiveStories(Pageable pageable);

  /**
   * Find inactive stories page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findInactiveStories(Pageable pageable);

  /**
   * Find all story by author page.
   *
   * @param author   the author
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findAllStoryByAuthor(Master author, Pageable pageable);

  /**
   * Find stories by title containing page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryJpa> findStoriesByTitleContaining(String title, Pageable pageable);

  /**
   * Find last added story.
   *
   * @return the story
   */
  StoryJpa findLastAddedStory();

  /**
   * Create story.
   *
   * @param Story the story
   * @return the story
   */
  StoryJpa createStory(StoryJpa Story);

  /**
   * Update story.
   *
   * @param Story the story
   * @return the story
   */
  StoryJpa updateStory(StoryJpa Story);

  /**
   * Status story by uuid.
   *
   * @param status the status
   * @param uuid   the uuid
   */
  void statusStoryByUuid(StatusRequest status, UUID uuid);

  /**
   * Delete story by uuid.
   *
   * @param uuid the uuid
   */
  void deleteStoryByUuid(UUID uuid);
}
