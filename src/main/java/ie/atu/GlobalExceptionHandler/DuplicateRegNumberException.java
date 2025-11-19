package ie.atu.GlobalExceptionHandler;

public class DuplicateRegNumberException extends RuntimeException {
    public DuplicateRegNumberException(String message) {
        super(message);
    }
}
