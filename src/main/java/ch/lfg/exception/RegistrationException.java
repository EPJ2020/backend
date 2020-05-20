package ch.lfg.exception;

/**
 * Custom Exception class for cases of invalid registration details
 */
public class RegistrationException extends Exception {

  /**
   * @param message contains the message that the user recieving the exception will see
   */
  public RegistrationException(String message) {
    super(message);
  }
}
