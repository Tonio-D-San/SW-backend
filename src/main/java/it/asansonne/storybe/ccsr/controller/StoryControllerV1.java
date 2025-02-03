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
import it.asansonne.storybe.dto.request.StoryRequest;
import it.asansonne.storybe.dto.request.StoryUpdateRequest;
import it.asansonne.storybe.dto.response.StoryResponse;
import it.asansonne.storybe.exception.ExceptionMessage;
import it.asansonne.storybe.util.swagger.schema.PageStorySchema;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The interface Story controller v1.
 */
public interface StoryControllerV1 {
  /**
   * The constant STORY_ORDER.
   */
  String STORY_ORDER = "creationDate";

  /**
   * Find story by uuid story response.
   *
   * @param uuid the uuid
   * @return the story response
   */
  @Operation(summary = "Story find by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Story has been found by uuid",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StoryResponse.class))),
      @ApiResponse(responseCode = "204", description = "No story found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the story",
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
          description = "Access to the story you were trying to reach is prohibited",
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
  StoryResponse findStoryByUuid(
      @Parameter(name = "uuid", description = "Story uuid",
          example = "081081d1-1777-4b59-8b4b-e9ab26186006") UUID uuid);

  /**
   * Find active stories page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All active stories found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Active Stories found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageStorySchema.class))),
      @ApiResponse(responseCode = "204", description = "No active stories found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the active stories",
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
          description = "Access to the active stories you were trying to reach is prohibited",
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
  Page<StoryResponse> findActiveStories(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find inactive stories page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All inactive stories found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Inactive stories found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageStorySchema.class))),
      @ApiResponse(responseCode = "204", description = "No inactive stories found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the inactive stories",
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
          description = "Access to the inactive stories you were trying to reach is prohibited",
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
  Page<StoryResponse> findInactiveStories(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find all stories by author page.
   *
   * @param page        the page
   * @param size        the size
   * @param direction   the direction
   * @param authorEmail the author email
   * @return the page
   */
  @Operation(summary = "All stories found by Author")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Stories found by author",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageStorySchema.class))),
      @ApiResponse(responseCode = "204", description = "No stories found by author",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the stories",
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
          description = "Access to the stories you were trying to reach is prohibited",
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
  Page<StoryResponse> findAllStoriesByAuthor(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "author-email", description = "story owner email") String authorEmail);

  /**
   * Find all stories by title containing page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param title     the title
   * @return the page
   */
  @Operation(summary = "All stories found by Title Containing")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Stories found by Title Containing",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageStorySchema.class))),
      @ApiResponse(responseCode = "204", description = "No stories found by Title Containing",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the stories",
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
          description = "Access to the stories you were trying to reach is prohibited",
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
  Page<StoryResponse> findAllStoriesByTitleContaining(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "title", description = "stories title") String title);

  /**
   * Find all stories sorted by field page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @param field     the field
   * @return the page
   */
  @Operation(summary = "All story found by sorted by field")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Story has been found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StoryResponse.class))),
      @ApiResponse(responseCode = "204", description = "No story found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the story",
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
          description = "Access to the story you were trying to reach is prohibited",
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
  Page<StoryResponse> findAllStoriesSortedByField(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction,
      @Parameter(name = "field", description = "stories field") String field);

  /**
   * Find last added story response.
   *
   * @return the story response
   */
  @Operation(summary = "Last story added")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Last story added",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageStorySchema.class))),
      @ApiResponse(responseCode = "204", description = "No last story found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the story",
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
          description = "Access to the story you were trying to reach is prohibited",
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
  StoryResponse findLastAddedStory();

  /**
   * Create story response entity.
   *
   * @param storyRequest the story request
   * @param builder      the builder
   * @return the response entity
   */
  @Operation(summary = "Story creation")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Story has been created",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StoryResponse.class))),
      @ApiResponse(responseCode = "204", description = "No story found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "400", description = "Story has validation errors",
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
          description = "You are not authorized to access the creation of a story",
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
          description = "Access to the creation of a story you are trying to"
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
          description = "The story cannot be created because the master is inactive",
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
          description = "Conflict to insert a new story",
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
  @RequestBody(description = "Story to add",
      required = true,
      content = @Content(
          schema = @Schema(implementation = StoryRequest.class)))
  ResponseEntity<StoryResponse> createStory(Principal principal,
                                            StoryRequest storyRequest,
                                            UriComponentsBuilder builder);


  /**
   * Update story by uuid story response.
   *
   * @param storyUpdateRequest the story update request
   * @param uuidStory          the uuid story
   * @return the story response
   * @throws Exception the exception
   */
  @Operation(summary = "Story update by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Story has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StoryResponse.class))),
      @ApiResponse(responseCode = "400", description = "Story has validation errors",
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
          description = "You are not authorized to access the update of a story",
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
          description = "Access to the update of a story you are trying to"
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
          description = "The story cannot be updated because the master is inactive",
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
          description = "Conflict when update a story",
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
  @RequestBody(description = "Story info to update",
      required = true,
      content = @Content(
          schema = @Schema(implementation = StoryUpdateRequest.class)))
  StoryResponse updateStoryByUuid(Principal principal,
                                  StoryUpdateRequest storyUpdateRequest,
                                  @Parameter(name = "uuid", description = "Story uuid",
                                      example = "081081d1-1777-4b59-8b4b-e9ab26186006")
                                  UUID uuidStory) throws Exception;

  /**
   * Status story by uuid.
   *
   * @param status      the status
   * @param commentUuid the comment uuid
   */
  @Operation(summary = "Change Status story by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Story has been disabled/activated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StoryResponse.class))),
      @ApiResponse(responseCode = "400", description = "Story has validation errors",
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
          description = "You are not authorized to access to the status of story",
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
  @RequestBody(description = "Story status",
      required = true,
      content = @Content(
          schema = @Schema(implementation = StatusRequest.class)))
  void statusStoryByUuid(Principal principal, StatusRequest status,
                         @Parameter(name = "uuid", description = "Story uuid",
                             example = "08fba211-60ca-45fc-b809-86bc2ad81dca")
                         @PathVariable("uuid") UUID commentUuid);

  /**
   * Delete story by uuid.
   *
   * @param uuid the uuid
   */
  @Operation(summary = "Story delete by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Story has been deleted"),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the delete of a story",
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
          description = "Access to the delete of a story you are trying to"
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
  void deleteStoryByUuid(@Parameter(name = "uuid", description = "Story uuid",
      example = "d8317c61-1ca9-4a3d-9501-ec70e74e50e6") @PathVariable("uuid") UUID uuid);
}