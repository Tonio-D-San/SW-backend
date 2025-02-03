package it.asansonne.storybe.ccsr.component.impl;

import static it.asansonne.storybe.constant.MessageConstant.FORBIDDEN;
import static it.asansonne.storybe.constant.MessageConstant.PERSON_NOT_FOUND;
import static it.asansonne.storybe.constant.MessageConstant.STORY_NOT_FOUND;
import static it.asansonne.storybe.util.FileUtil.isAdmin;

import it.asansonne.storybe.ccsr.component.StoryComponent;
import it.asansonne.storybe.ccsr.service.MasterService;
import it.asansonne.storybe.ccsr.service.StoryService;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.StoryRequest;
import it.asansonne.storybe.dto.request.StoryUpdateRequest;
import it.asansonne.storybe.dto.response.StoryResponse;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.mapper.RequestModelMapper;
import it.asansonne.storybe.mapper.ResponseModelMapper;
import it.asansonne.storybe.model.jpa.Master;
import it.asansonne.storybe.model.jpa.StoryJpa;
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
 * The type Story component.
 */
@Component
@AllArgsConstructor
public class StoryComponentImpl implements StoryComponent {
  private final StoryService storyService;
  private final MasterService masterService;
  private final ResponseModelMapper<StoryJpa, StoryResponse> responseModelMapper;
  private final RequestModelMapper<StoryRequest, StoryJpa> requestModelMapper;


  @Override
  public StoryResponse findStoryByUuid(UUID uuid) {
    return responseModelMapper.toDto(storyService.findStoryByUuid(uuid)
        .orElseThrow(() -> new EntityNotFoundException(STORY_NOT_FOUND)));
  }

  @Override
  public Page<StoryResponse> findActiveStories(Pageable pageable) {
    return responseModelMapper.toDto(storyService.findActiveStories(pageable), pageable);
  }

  @Override
  public Page<StoryResponse> findInactiveStories(Pageable pageable) {
    return responseModelMapper.toDto(storyService.findInactiveStories(pageable), pageable);
  }

  @Override
  public Page<StoryResponse> findAllStoriesByAuthor(Pageable pageable, String masterEmail) {
    Master master = masterService.findMasterByEmail(masterEmail)
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
    Page<StoryJpa> stories = storyService.findAllStoryByAuthor(master, pageable);
    return responseModelMapper.toDto(stories, pageable);
  }

  @Override
  public Page<StoryResponse> findStoriesByTitleContaining(String title, Pageable pageable) {
    return responseModelMapper.toDto(storyService.findStoriesByTitleContaining(title, pageable),
        pageable);
  }

  @Override
  public Page<StoryResponse> findAllStoriesSortedByField(Integer page, Integer size,
                                                         String direction, String field) {
    PageRequest pageable = sorting(page, size, direction, field);
    return responseModelMapper.toDto(storyService.findActiveStories(pageable), pageable);
  }

  @Override
  public StoryResponse findLastAddedStory() {
    return responseModelMapper.toDto(storyService.findLastAddedStory());
  }

  @Override
  public StoryResponse createStory(Principal principal, StoryRequest storyRequest) {
    StoryJpa story = requestModelMapper.toModel(storyRequest);
    story.setAuthor(findMaster(principal));
    story.setUuid(UUID.randomUUID());
    long creationDate = Instant.now().toEpochMilli();
    story.setCreationDate(creationDate);
    story.setLastEditDate(creationDate);
    story.setIsActive(true);
    return responseModelMapper.toDto(storyService.createStory(story));
  }

  @Override
  public StoryResponse updateStoryByUuid(Principal principal, StoryUpdateRequest storyUpdateRequest,
                                         UUID uuidStory) {
    StoryJpa story = storyService.findStoryByUuid(uuidStory)
        .orElseThrow(() -> new NotFoundException(STORY_NOT_FOUND));
    if (findMaster(principal).getUuid().equals(story.getAuthor().getUuid())) {
      story.setTitle(storyUpdateRequest.getTitle());
      story.setIsCompleted(storyUpdateRequest.getSolution() == null);
      story.setDescription(storyUpdateRequest.getProblem());
      return responseModelMapper.toDto(storyService.updateStory(story));
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  @Override
  public void statusStoryByUuid(Principal principal, StatusRequest status, UUID storyUuid) {
    StoryJpa story = storyService.findStoryByUuid(storyUuid)
        .orElseThrow(() -> new NotFoundException(STORY_NOT_FOUND));
    Master master = findMaster(principal);
    if (isAdmin(master) || status.getIsActive().equals(false)
        && (master.getUuid().equals(story.getAuthor().getUuid()))) {
      storyService.statusStoryByUuid(status, storyUuid);
    } else {
      throw new AccessDeniedException(FORBIDDEN);
    }
  }

  @Override
  public void deleteStoryByUuid(UUID uuid) {
    storyService.deleteStoryByUuid(uuid);
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

  private Master findMaster(Principal principal) {
    return masterService.findMasterByUuid(
            UUID.fromString(principal.getName().split("[,\\[\\]\\s]+")[1]))
        .orElseThrow(() -> new EntityNotFoundException(PERSON_NOT_FOUND));
  }
}
