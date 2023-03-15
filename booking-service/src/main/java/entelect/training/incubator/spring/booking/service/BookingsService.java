package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.Booking;
import entelect.training.incubator.spring.booking.model.BookingsSearchRequest;
import entelect.training.incubator.spring.booking.model.SearchType;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public class BookingsService {
    private final BookingRepository bookingRepository;

    public BookingsService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        Iterable<Booking> bookingIterable = bookingRepository.findAll();

        List<Booking> result = new ArrayList<>();
        bookingIterable.forEach(result::add);

        return result;
    }

    public Booking getBooking(Integer id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }

//    public Booking searchBookings(BookingsSearchRequest searchRequest) {
//        Map<SearchType, Supplier<Optional<Booking>>>searchStrategies = new HashMap<>();
//
//        searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findByCustomerID(searchRequest.getCustomerID()));
//        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findByReferenceNumber(searchRequest.getReferenceNumber()));
//
//        Optional<Booking> bookingOptional = searchStrategies.get(searchRequest.getSearchType()).get();
//
//        return bookingOptional.orElse(null);
//    }

    public List<Booking> searchBookings(BookingsSearchRequest searchRequest) {
        Map<SearchType, Supplier<Optional<List<Booking>>>>searchStrategies = new HashMap<>();

        searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findByCustomerID(searchRequest.getCustomerID()));
        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findByReferenceNumber(searchRequest.getReferenceNumber()));

        Optional<List<Booking>> bookingOptional = searchStrategies.get(searchRequest.getSearchType()).get();

        return bookingOptional.orElse(null);
    }
}
