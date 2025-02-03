package it.asansonne.storybe.exception.custom;

/**
 * The type Inactive person exception.
 */
public class StoryInactiveException extends RuntimeException {

  /**
   * Instantiates a new Inactive person exception.
   */
  @SuppressWarnings("unused")
  public StoryInactiveException() {
    super();
  }

  /**
   * Instantiates a new Inactive person exception.
   *
   * @param message the message
   */
  public StoryInactiveException(String message) {
    super(message);
  }
}
