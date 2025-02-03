package it.asansonne.storybe.ccsr.service.impl;

import static it.asansonne.storybe.constant.MessageConstant.STORY_ACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.STORY_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.STORY_INACTIVE_EMPTY;
import static it.asansonne.storybe.constant.MessageConstant.STORY_NOT_FOUND;

import it.asansonne.storybe.ccsr.repository.jpa.StoryRepository;
import it.asansonne.storybe.ccsr.service.StoryService;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.model.jpa.Master;
import it.asansonne.storybe.model.jpa.StoryJpa;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * The type Story service.
 */
@Service
@AllArgsConstructor
public final class StoryServiceImpl implements StoryService {
  private final StoryRepository storyRepository;

  @Override
  public Optional<StoryJpa> findStoryByUuid(UUID uuid) {
    Optional<StoryJpa> story = storyRepository.findStoryByUuid(uuid);
    if (story.isEmpty()) {
      throw new EntityNotFoundException(STORY_EMPTY);
    }
    return story;
  }

  @Override
  public Page<StoryJpa> findActiveStories(Pageable pageable) {
    Page<StoryJpa> stories = storyRepository.findStoriesByIsActiveTrue(pageable);
    if (stories.isEmpty()) {
      throw new EntityNotFoundException(STORY_ACTIVE_EMPTY);
    }
    return stories;
  }

  @Override
  public Page<StoryJpa> findInactiveStories(Pageable pageable) {
    Page<StoryJpa> stories = storyRepository.findStoriesByIsActiveFalse(pageable);
    if (stories.isEmpty()) {
      throw new EntityNotFoundException(STORY_INACTIVE_EMPTY);
    }
    return stories;
  }

  @Override
  public Page<StoryJpa> findAllStoryByAuthor(Master author, Pageable pageable) {
    Page<StoryJpa> stories = storyRepository.findAllStoriesByAuthor(author, pageable);
    if (stories.isEmpty()) {
      throw new EntityNotFoundException(STORY_EMPTY);
    }
    return stories;
  }

  @Override
  public Page<StoryJpa> findStoriesByTitleContaining(String title, Pageable pageable) {
    Page<StoryJpa> stories =
        storyRepository.findStoriesByTitleContainingIgnoreCase(title.trim(), pageable);
    if (stories.isEmpty()) {
      throw new EntityNotFoundException(STORY_EMPTY);
    }
    return stories;
  }

  @Override
  public StoryJpa findLastAddedStory() {
    StoryJpa story = storyRepository.findLastAddedStory();
    if (story == null) {
      throw new EntityNotFoundException(STORY_NOT_FOUND);
    }
    return story;
  }

  @Override
  public StoryJpa createStory(StoryJpa story) {
    return storyRepository.save(story);
  }

  @Override
  public StoryJpa updateStory(StoryJpa story) {
    return storyRepository.save(story);
  }

  @Override
  public void statusStoryByUuid(StatusRequest status, UUID uuid) {
    StoryJpa story = findStory(uuid);
    story.setIsActive(status.getIsActive());
    storyRepository.save(story);
  }

  @Override
  public void deleteStoryByUuid(UUID uuid) {
    storyRepository.delete(findStory(uuid));
  }

  private StoryJpa findStory(UUID uuid) {
    return storyRepository.findStoryByUuid(uuid)
        .orElseThrow(() -> new NotFoundException(STORY_NOT_FOUND));
  }
}
