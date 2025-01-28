package it.asansonne.storybe.exception.handler;

import it.asansonne.storybe.exception.ExceptionMessage;
import it.asansonne.storybe.exception.custom.InactivePersonException;
import it.asansonne.storybe.exception.custom.NotFoundException;
import it.asansonne.storybe.exception.custom.ParentCreationDateException;
import it.asansonne.storybe.exception.custom.TopicInactiveException;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * The type Application exception handler.
 */
@RestControllerAdvice
final class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  private ExceptionMessage runtimeExceptionHandler(RuntimeException e) {
    return new ExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ExceptionHandler(EntityNotFoundException.class)
  private void handleEmptyException(EntityNotFoundException e) {
    System.out.println(e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ExceptionMessage validationExceptionHandler(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      errors.put(fieldName, error.getDefaultMessage());
    });
    return new ExceptionMessage(HttpStatus.BAD_REQUEST, "Validation Error", errors);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  private ExceptionMessage validationExceptionHandler(HttpMessageNotReadableException e) {
    return new ExceptionMessage(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({NoResourceFoundException.class, NotFoundException.class})
  private ExceptionMessage noResourceExceptionHandler(Exception e) {
    return new ExceptionMessage(HttpStatus.NOT_FOUND, "Error: " + e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  private ExceptionMessage argumentErrorExceptionHandler(IllegalArgumentException e) {
    return new ExceptionMessage(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(DataIntegrityViolationException.class)
  private ExceptionMessage handleConflictException(DataIntegrityViolationException ex) {
    return new ExceptionMessage(HttpStatus.CONFLICT, ex.getMostSpecificCause().getMessage());
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler({InactivePersonException.class, TopicInactiveException.class})
  private ExceptionMessage handleMethodNotAllowedException(Exception ex) {
    return new ExceptionMessage(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ParentCreationDateException.class)
  private ExceptionMessage handleParentCreationDateException(ParentCreationDateException ex) {
    return new ExceptionMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
  }
}
