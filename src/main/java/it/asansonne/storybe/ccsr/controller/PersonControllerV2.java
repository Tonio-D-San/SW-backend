package it.asansonne.storybe.ccsr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.asansonne.storybe.dto.request.PersonGroupRequest;
import it.asansonne.storybe.dto.request.PersonRequest;
import it.asansonne.storybe.dto.request.PersonUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.PersonResponse;
import it.asansonne.storybe.exception.ExceptionMessage;
import it.asansonne.storybe.util.swagger.schema.PagePersonSchema;
import java.security.Principal;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The interface Person controller v 2.
 */
public interface PersonControllerV2 {
  /**
   * The constant SURNAME.
   */
  String SURNAME = "surname";

  /**
   * Find person by uuid person response.
   *
   * @param uuid the uuid
   * @return the person response
   */
  @Operation(summary = "Person find by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Person has been found by uuid",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PersonResponse.class))),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the person",
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
          description = "Access to the person you were trying to reach is prohibited",
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
  @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
  PersonResponse findPersonByUuid(@Parameter(name = "uuid", description = "The person uuid",
      example = "08fba211-60ca-45fc-b809-86bc2ad81dca") @PathVariable UUID uuid)
      throws JsonProcessingException;

  /**
   * Find all persons page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All persons found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PagePersonSchema.class))),
      @ApiResponse(responseCode = "204", description = "No persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the persons",
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
          description = "Access to the persons you were trying to reach is prohibited",
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
  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  Page<PersonResponse> findAllPersons(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find active persons page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All active persons found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Active persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PagePersonSchema.class))),
      @ApiResponse(responseCode = "204", description = "No active persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the active persons",
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
          description = "Access to the active persons you were trying to reach is prohibited",
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
  @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
  Page<PersonResponse> findActivePersons(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find inactive persons page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All inactive persons found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Inactive persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PagePersonSchema.class))),
      @ApiResponse(responseCode = "204", description = "No inactive persons found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the inactive persons",
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
          description = "Access to the inactive persons you were trying to reach is prohibited",
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
  @GetMapping(value = "/inactive", produces = MediaType.APPLICATION_JSON_VALUE)
  Page<PersonResponse> findInactivePersons(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Create user response entity.
   *
   * @param personRequest the person request
   * @param builder         the builder
   * @return the response entity
   */
  @Operation(summary = "User creation")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User has been created",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PersonResponse.class))),
      @ApiResponse(responseCode = "204", description = "No user found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "400", description = "User has validation errors",
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
          description = "You are not authorized to access the creation of a user",
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
          description = "Access to the creation of a user you are trying to"
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
          description = "The user cannot be created because the person is inactive",
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
          description = "Conflict to insert a new user",
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
  @RequestBody(description = "User to add",
      required = true,
      content = @Content(
          schema = @Schema(implementation = PersonRequest.class)))
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<PersonResponse> createPerson(PersonRequest personRequest,
                                              UriComponentsBuilder builder
  ) throws JsonProcessingException;

  /**
   * Update user person keycloak response.
   *
   * @param uuid                  the uuid
   * @return the person keycloak response
   */
  @Operation(summary = "User update by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PersonResponse.class))),
      @ApiResponse(responseCode = "400", description = "User has validation errors",
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
          description = "You are not authorized to access the update of a user",
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
          description = "Access to the update of a user you are trying to"
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
          description = "The user cannot be updated because the person is inactive",
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
          description = "Conflict when update a user",
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
  @RequestBody(description = "User info to update",
      required = true,
      content = @Content(
          schema = @Schema(implementation = PersonUpdateRequest.class)))
  @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  PersonResponse updatePersonByUuid(Principal principal,
      PersonUpdateRequest personRequest,
                                    @PathVariable("uuid") UUID uuid
  );

  /**
   * Update user person keycloak response.
   *
   * @param uuid                  the uuid
   * @return the person keycloak response
   */
  @Operation(summary = "User update groups by person uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User groups has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PersonResponse.class))),
      @ApiResponse(responseCode = "400", description = "User has validation errors",
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
          description = "You are not authorized to access the update of a user",
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
          description = "Access to the update of a user you are trying to"
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
          description = "The user cannot be updated because the person is inactive",
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
          description = "Conflict when update a user",
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
  @RequestBody(description = "User group to update",
      required = true,
      content = @Content(
          schema = @Schema(implementation = PersonGroupRequest.class)))
  @PatchMapping(value = "/groups/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  PersonResponse updateGroupByPersonUuid(PersonGroupRequest personGroupRequest,
                                         @PathVariable("uuid") UUID uuid
  );

  /**
   * Update user status.
   *
   * @param uuid                  the uuid
   * @param status the person status
   */
  @Operation(summary = "Update user status by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User status has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = StatusRequest.class))),
      @ApiResponse(responseCode = "400", description = "User has validation errors",
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
          description = "You are not authorized to access the update status of a user",
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
          description = "Access to the update status of a user you are trying to"
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
          description = "The user status cannot be updated because the person is inactive",
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
          description = "Conflict when update a user status",
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
  @RequestBody(description = "User status info to updated",
      required = true,
      content = @Content(
          schema = @Schema(implementation = StatusRequest.class)))
  @PatchMapping(value = "/status/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  void updateStatusPersonByUuid(@PathVariable("uuid") UUID uuid, StatusRequest status);
}
