package ie.atu.GlobalExceptionHandler;

public class InvalidBookingDataException extends RuntimeException {
    public InvalidBookingDataException(String message) {
        super(message);
    }
}
