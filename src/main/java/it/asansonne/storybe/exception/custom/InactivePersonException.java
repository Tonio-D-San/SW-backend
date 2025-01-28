package it.asansonne.storybe.exception.custom;

/**
 * The type Inactive person exception.
 */
public class InactivePersonException extends RuntimeException {

  /**
   * Instantiates a new Inactive person exception.
   */
  @SuppressWarnings("unused")
  public InactivePersonException() {
    super();
  }

  /**
   * Instantiates a new Inactive person exception.
   *
   * @param message the message
   */
  public InactivePersonException(String message) {
    super(message);
  }
}
