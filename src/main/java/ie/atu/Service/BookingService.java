package ie.atu.Service;


import ie.atu.GlobalExceptionHandler.BookingNotFoundException;
import ie.atu.GlobalExceptionHandler.DuplicateRegNumberException;
import ie.atu.GlobalExceptionHandler.InvalidBookingDataException;
import ie.atu.Model.Booking;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final List<Booking> storage = new ArrayList<>();

    public Optional<Booking> getBookingByRegNumber(@Valid String regNumber){

        for(Booking booking : storage){
            if(booking.getRegNumber().equals(regNumber)){
                return Optional.of(booking);
            }
        }

        return Optional.empty();
    }


    public List<Booking> getAllBookings(){
        return new ArrayList<>(storage);
    }

    public Booking create(Booking booking){

        if(getBookingByRegNumber(booking.getRegNumber()).isPresent()){

            throw new DuplicateRegNumberException("Booking already exists");

        }

        storage.add(booking);

        return booking;

    }


    public Booking remove(Booking booking){

        if(getBookingByRegNumber(booking.getRegNumber()).isPresent()){

            storage.remove(booking);

            return booking;
        }

        throw new BookingNotFoundException("Booking does not exist");


    }


    public Booking update(Booking booking){

        if(getBookingByRegNumber(booking.getRegNumber()).isPresent()){

            storage.set(storage.indexOf(getBookingByRegNumber(booking.getRegNumber()).get()),booking);

            return booking;
        }

        throw new BookingNotFoundException("Booking does not exist");


    }



}
