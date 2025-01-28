package it.asansonne.storybe.ccsr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.request.TopicRequest;
import it.asansonne.storybe.dto.request.TopicUpdateRequest;
import it.asansonne.storybe.dto.response.TopicResponse;
import it.asansonne.storybe.exception.ExceptionMessage;
import it.asansonne.storybe.util.swagger.schema.PageTopicSchema;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The interface Topic controller v1.
 */
public interface TopicControllerV2 {
  /**
   * The constant TOPIC_ORDER.
   */
  String TOPIC_ORDER = "creationDate";

  /**
   * Find topic by uuid topic response.
   *
   * @param uuid the uuid
   * @return the topic response
   */
  @Operation(summary = "Topic find by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topic has been found by uuid",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = TopicResponse.class))),
      @ApiResponse(responseCode = "204", description = "No topic found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                           }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the topic you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "404",
          description = "No resource found by uuid in the URI",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "404 - NOT FOUND",
                      value = """
                          {
                          "status": "NOT_FOUND",
                          "message": \
                          "Not found message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  TopicResponse findTopicByUuid(
      @Parameter(name = "uuid", description = "Topic uuid",
          example = "081081d1-1777-4b59-8b4b-e9ab26186006") UUID uuid);

  /**
   * Find active topics page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All active topics found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Active Topics found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageTopicSchema.class))),
      @ApiResponse(responseCode = "204", description = "No active topics found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the active topics",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the active topics you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  Page<TopicResponse> findActiveTopics(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find inactive topics page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All inactive topics found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Inactive topics found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageTopicSchema.class))),
      @ApiResponse(responseCode = "204", description = "No inactive topics found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the inactive topics",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the inactive topics you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  Page<TopicResponse> findInactiveTopics(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find all topics by author page.
   *
   * @param page        the page
   * @param size        the size
   * @param direction   the direction
   * @param authorEmail the author email
   * @return the page
   */
  @Operation(summary = "All topics found by Author")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topics found by author",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageTopicSchema.class))),
      @ApiResponse(responseCode = "204", description = "No topics found by author",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the topics",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the topics you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  Page<TopicResponse> findAllTopicsByAuthor(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "author-email", description = "topic owner email") String authorEmail);

  /**
   * Find all topics by title containing page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param title     the title
   * @return the page
   */
  @Operation(summary = "All topics found by Title Containing")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topics found by Title Containing",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageTopicSchema.class))),
      @ApiResponse(responseCode = "204", description = "No topics found by Title Containing",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the topics",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the topics you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  Page<TopicResponse> findAllTopicsByTitleContaining(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "title", description = "topics title") String title);

  /**
   * Find all topics sorted by field page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param field     the field
   * @return the page
   */
  @Operation(summary = "All topic found by sorted by field")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topic has been found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = TopicResponse.class))),
      @ApiResponse(responseCode = "204", description = "No topic found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                           }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the topic you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "404",
          description = "No resource found by creation date and title in the URI",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "404 - NOT FOUND",
                      value = """
                          {
                          "status": "NOT_FOUND",
                          "message": \
                          "Not found message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  Page<TopicResponse> findAllTopicsSortedByField(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "field", description = "topics field") String field);

  /**
   * Find last added topic response.
   *
   * @return the topic response
   */
  @Operation(summary = "Last topic added")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Last topic added",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageTopicSchema.class))),
      @ApiResponse(responseCode = "204", description = "No last topic found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the topic you were trying to reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  TopicResponse findLastAddedTopic();

  /**
   * Create topic response entity.
   *
   * @param topicRequest the topic request
   * @param builder      the builder
   * @return the response entity
   */
  @Operation(summary = "Topic creation")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Topic has been created",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = TopicResponse.class))),
      @ApiResponse(responseCode = "204", description = "No topic found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "400", description = "Topic has validation errors",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "400 - BAD REQUEST",
                      value = """
                          {
                          "status": "BAD REQUEST",
                          "message": \
                          "Bad Request message"
                          , "validations": \
                          {"field":"constraint violation message"}}"""
                  )
              },

              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the creation of a topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the creation of a topic you are trying to"
              + " reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "405",
          description = "The topic cannot be created because the person is inactive",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "405 - METHOD NOT ALLOWED",
                      value = """
                          {
                          "status": "METHOD_NOT_ALLOWED",
                          "message": \
                          "Method not allowed message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "409",
          description = "Conflict to insert a new topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "409 - CONFLICT",
                      value = """
                          {
                          "status": "CONFLICT",
                          "message": \
                          "Conflict message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  @RequestBody(description = "Topic to add",
      required = true,
      content = @Content(
          schema = @Schema(implementation = TopicRequest.class)))
  ResponseEntity<TopicResponse> createTopic(Principal principal,
                                            TopicRequest topicRequest,
                                            UriComponentsBuilder builder);


  /**
   * Update topic by uuid topic response.
   *
   * @param topicUpdateRequest the topic update request
   * @param uuidTopic          the uuid topic
   * @return the topic response
   * @throws Exception the exception
   */
  @Operation(summary = "Topic update by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topic has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = TopicResponse.class))),
      @ApiResponse(responseCode = "400", description = "Topic has validation errors",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "400 - BAD REQUEST",
                      value = """
                          {
                          "status": "BAD REQUEST",
                          "message": \
                          "Bad Request message"
                          , "validations": \
                          {"field":"constraint violation message"}}"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the update of a topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the update of a topic you are trying to"
              + " reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "404",
          description = "No resource found by uuid in the URI",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "404 - NOT FOUND",
                      value = """
                          {
                          "status": "NOT_FOUND",
                          "message": \
                          "Not found message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "405",
          description = "The topic cannot be updated because the person is inactive",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "405 - METHOD NOT ALLOWED",
                      value = """
                          {
                          "status": "METHOD_NOT_ALLOWED",
                          "message": \
                          "Method not allowed message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "409",
          description = "Conflict when update a topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "409 - CONFLICT",
                      value = """
                          {
                          "status": "CONFLICT",
                          "message": \
                          "Conflict message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  @RequestBody(description = "Topic info to update",
      required = true,
      content = @Content(
          schema = @Schema(implementation = TopicUpdateRequest.class)))

  TopicResponse updateTopicByUuid(Principal principal,
                                  TopicUpdateRequest topicUpdateRequest,
                                  @Parameter(name = "uuid", description = "Topic uuid",
                                      example = "081081d1-1777-4b59-8b4b-e9ab26186006")
                                  UUID uuidTopic) throws Exception;

  /**
   * Status topic by uuid.
   *
   * @param status      the status
   * @param commentUuid the comment uuid
   */
  @Operation(summary = "Change Status topic by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Topic has been disabled/activated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = TopicResponse.class))),
      @ApiResponse(responseCode = "400", description = "Topic has validation errors",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "400 - BAD REQUEST",
                      value = """
                          {
                          "status": "BAD REQUEST",
                          "message": \
                          "Bad Request message"
                          , "validations": \
                          {"field":"constraint violation message"}}"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access to the status of topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "404",
          description = "No resource found by uuid in the URI",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "404 - NOT FOUND",
                      value = """
                          {
                          "status": "NOT_FOUND",
                          "message": \
                          "Not found message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "409",
          description = "Conflict when deactivate ",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "409 - CONFLICT",
                      value = """
                          {
                          "status": "CONFLICT",
                          "message": \
                          "Conflict message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  @RequestBody(description = "Topic status",
      required = true,
      content = @Content(
          schema = @Schema(implementation = StatusRequest.class)))
  void statusTopicByUuid(Principal principal, StatusRequest status,
                         @Parameter(name = "uuid", description = "Topic uuid",
                             example = "08fba211-60ca-45fc-b809-86bc2ad81dca")
                         @PathVariable("uuid") UUID commentUuid);

  /**
   * Delete topic by uuid.
   *
   * @param uuid the uuid
   */
  @Operation(summary = "Topic delete by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Topic has been deleted"),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the delete of a topic",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "401 - UNAUTHORIZED",
                      value = """
                          {
                          "status": "UNAUTHORIZED",
                          "message": \
                          "Unauthorized message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "403",
          description = "Access to the delete of a topic you are trying to"
              + " reach is prohibited",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "403 - FORBIDDEN",
                      value = """
                          {
                          "status": "FORBIDDEN",
                          "message": \
                          "Forbidden message"
                          }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class))),
      @ApiResponse(responseCode = "404",
          description = "No resource found by uuid in the URI",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              examples = {
                  @ExampleObject(
                      name = "404 - NOT FOUND",
                      value = """
                          {
                          "status": "NOT_FOUND",
                          "message": \
                          "Not found message"
                          , "validations": \
                          null }"""
                  )
              },
              schema = @Schema(implementation = ExceptionMessage.class)))})
  void deleteTopicByUuid(@Parameter(name = "uuid", description = "Topic uuid",
      example = "d8317c61-1ca9-4a3d-9501-ec70e74e50e6") @PathVariable("uuid") UUID uuid);
}