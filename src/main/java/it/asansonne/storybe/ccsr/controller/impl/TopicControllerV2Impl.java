package it.asansonne.storybe.ccsr.controller.impl;

import static it.asansonne.storybe.constant.SharedConstant.ADMIN_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.ADMIN_USER_ROLES;
import static it.asansonne.storybe.constant.SharedConstant.USER_ROLES;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.asansonne.storybe.ccsr.controller.TopicControllerV2;
import it.asansonne.storybe.ccsr.component.TopicComponent;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.TopicRequest;
import it.asansonne.storybe.dto.request.TopicUpdateRequest;
import it.asansonne.storybe.dto.response.TopicResponse;
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
 * The type Topic controller v2.
 */
@RestController
@RequestMapping("api/v2/topics")
@RequiredArgsConstructor
@Tag(name = "TopicController V2")
@PreAuthorize(ADMIN_USER_ROLES)
public class TopicControllerV2Impl implements TopicControllerV2 {
  private final TopicComponent topicComponent;

  @Override
  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public TopicResponse findTopicByUuid(
      @PathVariable("uuid") UUID uuid) {
    return topicComponent.findTopicByUuid(uuid);
  }

  @Override
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<TopicResponse> findActiveTopics(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc")
      String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), TOPIC_ORDER));
    return topicComponent.findActiveTopics(pageRequest);
  }

  @Override
  @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_ROLES)
  public Page<TopicResponse> findInactiveTopics(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), TOPIC_ORDER));
    return topicComponent.findInactiveTopics(pageRequest);
  }

  @Override
  @GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<TopicResponse> findAllTopicsByAuthor(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "author-email") String authorEmail) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), TOPIC_ORDER));
    return topicComponent.findAllTopicsByAuthor(pageRequest, authorEmail);
  }

  @Override
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<TopicResponse> findAllTopicsByTitleContaining(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "title") String title) {
    PageRequest pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(direction), TOPIC_ORDER));
    return topicComponent.findTopicsByTitleContaining(title, pageRequest);
  }

  @Override
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public Page<TopicResponse> findAllTopicsSortedByField(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
      @RequestParam(value = "field") String field) {
    return topicComponent.findAllTopicsSortedByField(page, size, direction, field);
  }

  @Override
  @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public TopicResponse findLastAddedTopic() {
    return topicComponent.findLastAddedTopic();
  }

  @Override
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(USER_ROLES)
  public ResponseEntity<TopicResponse> createTopic(Principal principal,
                                                   @Valid @RequestBody TopicRequest topicRequest,
                                                   UriComponentsBuilder builder) {
    TopicResponse response = topicComponent.createTopic(principal, topicRequest);
    URI location = builder
        .path("api/v2/topicJpas/")
        .buildAndExpand(response)
        .toUri();
    return ResponseEntity.created(location).body(response);
  }

  @Override
  @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(USER_ROLES)
  public TopicResponse updateTopicByUuid(Principal principal,
                                         @Valid @RequestBody TopicUpdateRequest topicUpdateRequest,
                                         @PathVariable("uuid") UUID uuidTopic) throws Exception {
    return topicComponent.updateTopicByUuid(principal, topicUpdateRequest, uuidTopic);
  }

  @Override
  @PatchMapping(value = "/status/{uuid}")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(ADMIN_USER_ROLES)
  public void statusTopicByUuid(Principal principal,
                                @RequestBody StatusRequest status,
                                @PathVariable("uuid") UUID topicUuid) {
    topicComponent.statusTopicByUuid(principal, status, topicUuid);
  }

  @Override
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "/{uuid}")
  @PreAuthorize(ADMIN_ROLES)
  public void deleteTopicByUuid(@PathVariable("uuid") UUID uuid) {
    topicComponent.deleteTopicByUuid(uuid);
  }
}
