package ie.atu.GlobalExceptionHandler;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message) {
        super(message);


    }
}
