package it.asansonne.storybe.exception.custom;

/**
 * The type Inactive master exception.
 */
public class InactiveMasterException extends RuntimeException {

  /**
   * Instantiates a new Inactive master exception.
   */
  @SuppressWarnings("unused")
  public InactiveMasterException() {
    super();
  }

  /**
   * Instantiates a new Inactive master exception.
   *
   * @param message the message
   */
  public InactiveMasterException(String message) {
    super(message);
  }
}
