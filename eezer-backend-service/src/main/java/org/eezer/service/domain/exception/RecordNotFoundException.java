package org.eezer.service.domain.exception;

/**
 * Exception thrown if a record was not found in the database.
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }

}
