package ie.atu.Controller;


import ie.atu.GlobalExceptionHandler.DuplicateRegNumberException;
import ie.atu.Model.Booking;
import ie.atu.Service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody @Valid Booking booking){

        Optional<Booking> maybe = bookingService.getBookingByRegNumber(booking.getRegNumber());

        if(maybe.isPresent()){

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Booking with RegNumber " + booking.getRegNumber() + " already exists");

        }

        bookingService.create(booking);


        return ResponseEntity
                .created(URI.create(("/api/bookings") + booking.getRegNumber()))
                .body("Booking with RegNumber " + booking.getRegNumber() + " created");


    }




    @GetMapping("/{regNumber}")
    public ResponseEntity<?> getBookingByRegNumber(@PathVariable @Valid String regNumber){

        Optional<Booking> maybe = bookingService.getBookingByRegNumber(regNumber);
        if(maybe.isPresent()){
            bookingService.getBookingByRegNumber(regNumber);

            return ResponseEntity.ok(maybe.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking with RegNumber " + regNumber + " does not exist");
        }


    }


    @PutMapping("/{regNumber}")
    public ResponseEntity<?> updateBooking(@PathVariable String regNumber,@RequestBody @Valid Booking booking){

        if(bookingService.getBookingByRegNumber(regNumber).isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking with RegNumber " + regNumber + " does not exist");

        }else{

            bookingService.update(booking);

            return ResponseEntity.ok("Booking with RegNumber " + regNumber + " updated!");

        }


    }


    @DeleteMapping("/{regNumber}")
    public ResponseEntity<?> deleteBooking(@PathVariable String regNumber){

        if(bookingService.getBookingByRegNumber(regNumber).isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Booking with RegNumber " + regNumber + " does not exist");


        }else{

            bookingService.remove(bookingService.getBookingByRegNumber(regNumber).get());

            return ResponseEntity.ok("Booking with RegNumber " + regNumber + " deleted!");
        }


    }



}
