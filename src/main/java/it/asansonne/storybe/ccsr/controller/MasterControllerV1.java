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
import it.asansonne.storybe.dto.request.MasterGroupRequest;
import it.asansonne.storybe.dto.request.MasterRequest;
import it.asansonne.storybe.dto.request.MasterUpdateRequest;
import it.asansonne.storybe.dto.request.StatusRequest;
import it.asansonne.storybe.dto.response.MasterResponse;
import it.asansonne.storybe.exception.ExceptionMessage;
import it.asansonne.storybe.util.swagger.schema.PageMasterSchema;
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
 * The interface Master controller v1.
 */
public interface MasterControllerV1 {
  /**
   * The constant SURNAME.
   */
  String SURNAME = "surname";

  /**
   * Find master by uuid master response.
   *
   * @param uuid the uuid
   * @return the master response
   */
  @Operation(summary = "Master find by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Master has been found by uuid",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = MasterResponse.class))),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the master",
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
          description = "Access to the master you were trying to reach is prohibited",
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
  MasterResponse findMasterByUuid(@Parameter(name = "uuid", description = "The master uuid",
      example = "08fba211-60ca-45fc-b809-86bc2ad81dca") @PathVariable UUID uuid)
      throws JsonProcessingException;

  /**
   * Find all masters page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All masters found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageMasterSchema.class))),
      @ApiResponse(responseCode = "204", description = "No masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the masters",
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
          description = "Access to the masters you were trying to reach is prohibited",
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
  Page<MasterResponse> findAllMasters(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find active masters page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All active masters found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Active masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageMasterSchema.class))),
      @ApiResponse(responseCode = "204", description = "No active masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the active masters",
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
          description = "Access to the active masters you were trying to reach is prohibited",
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
  Page<MasterResponse> findActiveMasters(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Find inactive masters page.
   *
   * @param page      the page
   * @param size      the size
   * @param direction the direction
   * @return the page
   */
  @Operation(summary = "All inactive masters found")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Inactive masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = PageMasterSchema.class))),
      @ApiResponse(responseCode = "204", description = "No inactive masters found",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
      @ApiResponse(responseCode = "401",
          description = "You are not authorized to access the inactive masters",
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
          description = "Access to the inactive masters you were trying to reach is prohibited",
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
  Page<MasterResponse> findInactiveMasters(
      @Parameter(name = "page", description = "page number") Integer page,
      @Parameter(name = "size", description = "element's number in page") Integer size,
      @Parameter(name = "direction", description = "order direction") String direction);

  /**
   * Create user response entity.
   *
   * @param masterRequest the master request
   * @param builder         the builder
   * @return the response entity
   */
  @Operation(summary = "User creation")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User has been created",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = MasterResponse.class))),
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
          description = "The user cannot be created because the master is inactive",
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
          schema = @Schema(implementation = MasterRequest.class)))
  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<MasterResponse> createMaster(MasterRequest masterRequest,
                                              UriComponentsBuilder builder
  ) throws JsonProcessingException;

  /**
   * Update user master keycloak response.
   *
   * @param uuid                  the uuid
   * @return the master keycloak response
   */
  @Operation(summary = "User update by uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = MasterResponse.class))),
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
          description = "The user cannot be updated because the master is inactive",
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
          schema = @Schema(implementation = MasterUpdateRequest.class)))
  @PatchMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  MasterResponse updateMasterByUuid(Principal principal,
      MasterUpdateRequest masterRequest,
                                    @PathVariable("uuid") UUID uuid
  );

  /**
   * Update user master keycloak response.
   *
   * @param uuid                  the uuid
   * @return the master keycloak response
   */
  @Operation(summary = "User update groups by master uuid")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User groups has been updated",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(implementation = MasterResponse.class))),
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
          description = "The user cannot be updated because the master is inactive",
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
          schema = @Schema(implementation = MasterGroupRequest.class)))
  @PatchMapping(value = "/groups/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  MasterResponse updateGroupByMasterUuid(MasterGroupRequest masterGroupRequest,
                                         @PathVariable("uuid") UUID uuid
  );

  /**
   * Update user status.
   *
   * @param uuid                  the uuid
   * @param status the master status
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
          description = "The user status cannot be updated because the master is inactive",
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
  void updateStatusMasterByUuid(@PathVariable("uuid") UUID uuid, StatusRequest status);
}
