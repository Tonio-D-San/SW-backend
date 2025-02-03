package it.asansonne.storybe.ccsr.component;

import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.StoryRequest;
import it.asansonne.storybe.dto.request.StoryUpdateRequest;
import it.asansonne.storybe.dto.response.StoryResponse;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface Story component.
 */
public interface StoryComponent {

  /**
   * Find story by uuid story response.
   *
   * @param uuid the uuid
   * @return the story response
   */
  StoryResponse findStoryByUuid(UUID uuid);

  /**
   * Find active storyJpa page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryResponse> findActiveStories(Pageable pageable);

  /**
   * Find inactive storyJpas page.
   *
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryResponse> findInactiveStories(Pageable pageable);

  /**
   * Find all storyJpas by author page.
   *
   * @param pageable    the pageable
   * @param masterEmail the master email
   * @return the page
   */
  Page<StoryResponse> findAllStoriesByAuthor(Pageable pageable, String masterEmail);

  /**
   * Find storyJpas by title containing page.
   *
   * @param title    the title
   * @param pageable the pageable
   * @return the page
   */
  Page<StoryResponse> findStoriesByTitleContaining(String title, Pageable pageable);

  /**
   * Find all storyJpas sorted by field page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param field     the field
   * @return the page
   */
  Page<StoryResponse> findAllStoriesSortedByField(Integer page, Integer size, String direction,
                                                 String field);

  /**
   * Find last added story response.
   *
   * @return the story response
   */
  StoryResponse findLastAddedStory();

  /**
   * Create story response.
   *
   * @param storyRequest the story request
   * @return the story response
   */
  StoryResponse createStory(Principal principal, StoryRequest storyRequest);

  /**
   * Update story by uuid story response.
   *
   * @param storyUpdateRequest the story update request
   * @param uuidStory          the uuid story
   * @return the story response
   * @throws Exception the exception
   */
  StoryResponse updateStoryByUuid(Principal principal, StoryUpdateRequest storyUpdateRequest,
                                  UUID uuidStory) throws Exception;

  /**
   * Status story by uuid.
   *
   * @param status    the status
   * @param uuid      the uuid
   */
  void statusStoryByUuid(Principal principal, StatusRequest status, UUID uuid);

  /**
   * Delete story by uuid.
   *
   * @param uuid the uuid
   */
  void deleteStoryByUuid(UUID uuid);
}
