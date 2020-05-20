package ch.lfg.exception;

/**
 * Custom Exception class for cases of invalid login details
 */
public class FailedLoginException extends Exception {

  /**
   * @param message contains the message that the user recieving the exception will see
   */
  public FailedLoginException(String message) {
    super(message);
  }
}
