package it.asansonne.storybe.exception.custom;

/**
 * The type Inactive person exception.
 */
public class TopicInactiveException extends RuntimeException {

  /**
   * Instantiates a new Inactive person exception.
   */
  @SuppressWarnings("unused")
  public TopicInactiveException() {
    super();
  }

  /**
   * Instantiates a new Inactive person exception.
   *
   * @param message the message
   */
  public TopicInactiveException(String message) {
    super(message);
  }
}
