package entelect.training.incubator.spring.booking.controller;

import entelect.training.incubator.spring.booking.model.*;
import entelect.training.incubator.spring.booking.service.BookingsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingsController {

    private final Logger LOGGER = LoggerFactory.getLogger(BookingsController.class);

    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        LOGGER.info("Processing booking creation request for booking={}", booking);

        final Booking savedBooking = bookingsService.createBooking(booking);

        LOGGER.trace("Booking created");
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ResponseEntity<?> getBookings() {
        LOGGER.info("Fetching all bookings");
        List<Booking> bookings = bookingsService.getBookings();

        if (!bookings.isEmpty()) {
            LOGGER.trace("Found bookings");
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }

        LOGGER.info("No bookings could be found");
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        LOGGER.info("Processing booking search request for booking id={}", id);
        Booking booking = this.bookingsService.getBooking(id);

        if (booking != null) {
            LOGGER.trace("Found booking");
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }

        LOGGER.trace("Booking not found");
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/search/reference")
    public ResponseEntity<?> searchCustomersWithRef(@RequestBody BookingsReferenceSearchRequest referenceNumber) {
//        SearchType request= SearchType.CUSTOMER_ID_SEARCH;
         LOGGER.info("Processing booking search request for reference number");

        List<Booking> customer = bookingsService.searchBookingsWithRef(referenceNumber);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }

        LOGGER.trace("Booking not found");
        return ResponseEntity.notFound().build();
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/search/customer")
    public ResponseEntity<?> searchCustomersWithID(@RequestBody BookingsIDSearchRequest customerID) {
//        SearchType request= SearchType.CUSTOMER_ID_SEARCH;
        LOGGER.info("Processing booking search request for customer ID");

        List<Booking> customer = bookingsService.searchBookingsWithCustomer(customerID);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        }else {
            LOGGER.trace("Booking not found");
            return ResponseEntity.notFound().build();
        }


    }

//    @PostMapping("/search")
//    public ResponseEntity<?> searchCustomers(@RequestBody BookingsSearchRequest searchRequest) {
//        LOGGER.info("Processing booking search request for request {}", searchRequest);
//
//        List<Booking> customer = bookingsService.searchBookings(searchRequest);
//
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        }
//
//        LOGGER.trace("Booking not found");
//        return ResponseEntity.notFound().build();
//    }
}
