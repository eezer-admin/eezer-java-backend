package org.eezer.service.domain.exception;

/**
 * Exception thrown if input is not valid.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

}
