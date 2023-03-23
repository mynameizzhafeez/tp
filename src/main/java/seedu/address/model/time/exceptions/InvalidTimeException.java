package seedu.address.model.time.exceptions;

/**
 * Represents a timing error.
 */
public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String errorMessage) {
        super(errorMessage);
    }
}
