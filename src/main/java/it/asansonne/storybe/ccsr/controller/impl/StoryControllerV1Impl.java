package it.asansonne.storybe.ccsr.controller.impl;

import static it.asansonne.storybe.constant.SharedConstant.ADMIN_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.ADMIN_USER_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.USER_ROLES;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.asansonne.storybe.ccsr.controller.StoryControllerV1;
import it.asansonne.storybe.ccsr.component.StoryComponent;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.StoryRequest;
import it.asansonne.storybe.dto.request.StoryUpdateRequest;
import it.asansonne.storybe.dto.response.StoryResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The type Story controller v1.
 */
@RestController
@RequestMapping("api/v1/stories")
@RequiredArgsConstructor
@Tag(name = "StoryController V1")
@PreAuthorize(ADMIN_USER_ROLES)
public class StoryControllerV1Impl implements StoryControllerV1 {
  private final StoryComponent storyComponent;

  @Override
  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public StoryResponse findStoryByUuid(
      @PathVariable("uuid") UUID uuid) {
    return storyComponent.findStoryByUuid(uuid);
  }

  @Override
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<StoryResponse> findActiveStories(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc")
      String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), STORY_ORDER));
    return storyComponent.findActiveStories(pageRequest);
  }

  @Override
  @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_ROLES)
  public Page<StoryResponse> findInactiveStories(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), STORY_ORDER));
    return storyComponent.findInactiveStories(pageRequest);
  }

  @Override
  @GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<StoryResponse> findAllStoriesByAuthor(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "author-email") String authorEmail) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), STORY_ORDER));
    return storyComponent.findAllStoriesByAuthor(pageRequest, authorEmail);
  }

  @Override
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<StoryResponse> findAllStoriesByTitleContaining(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "title") String title) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), STORY_ORDER));
    return storyComponent.findStoriesByTitleContaining(title, pageRequest);
  }

  @Override
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<StoryResponse> findAllStoriesSortedByField(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "field") String field) {
    return storyComponent.findAllStoriesSortedByField(page, size, direction, field);
  }

  @Override
  @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public StoryResponse findLastAddedStory() {
    return storyComponent.findLastAddedStory();
  }

  @Override
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(USER_ROLES)
  public ResponseEntity<StoryResponse> createStory(Principal principal,
                                                   @Valid @RequestBody StoryRequest storyRequest,
                                                   UriComponentsBuilder builder) {
    StoryResponse response = storyComponent.createStory(principal, storyRequest);
    URI location = builder
        .path("api/v2/storyJpas/")
        .buildAndExpand(response)
        .toUri();
    return ResponseEntity.created(location).body(response);
  }

  @Override
  @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(USER_ROLES)
  public StoryResponse updateStoryByUuid(Principal principal,
                                         @Valid @RequestBody StoryUpdateRequest storyUpdateRequest,
                                         @PathVariable("uuid") UUID uuidStory) throws Exception {
    return storyComponent.updateStoryByUuid(principal, storyUpdateRequest, uuidStory);
  }

  @Override
  @PatchMapping(value = "/status/{uuid}")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_USER_ROLES)
  public void statusStoryByUuid(Principal principal,
                                @RequestBody StatusRequest status,
                                @PathVariable("uuid") UUID storyUuid) {
    storyComponent.statusStoryByUuid(principal, status, storyUuid);
  }

  @Override
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{uuid}")
  @PreAuthorize(ADMIN_ROLES)
  public void deleteStoryByUuid(@PathVariable("uuid") UUID uuid) {
    storyComponent.deleteStoryByUuid(uuid);
  }
}
