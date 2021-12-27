package hu.nye.progtech.torpedo.service.exception;

/**
 * Exception class for failed attack.
 */
public class AttackException extends Exception {
    public AttackException(String message) {
        super(message);
    }
}
