package entelect.training.incubator.spring.booking.service;

import entelect.training.incubator.spring.booking.model.*;
import entelect.training.incubator.spring.booking.repository.BookingRepository;
import entelect.training.incubator.spring.customer.model.Customer;
import entelect.training.incubator.spring.flight.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

@Service
public class BookingsService {
    private final BookingRepository bookingRepository;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private final WebClient localApiClient;
    private final JmsTemplate jmsTemplate;


    public BookingsService(BookingRepository bookingRepository, WebClient localApiClient, JmsTemplate jmsTemplate) {
        this.bookingRepository = bookingRepository;
        this.localApiClient = localApiClient;
//        this.localApiClient = localApiClient;
        this.jmsTemplate=jmsTemplate;

    }

    public Booking createBooking(Booking booking) {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = getCustomer(booking.getCustomerID());
        Flight flight = getFlight(booking.getFlightID());

        if(flight==null || customer==null)
        {
            return null;
        }else {
        jmsTemplate.convertAndSend("booking", sendSMS(customer.getFirstName(), customer.getLastName(),
                flight.getFlightNumber(),flight.getDepartureTime().toLocalDate()));
            return bookingRepository.save(booking);
        }

    }

    public String sendSMS(String name, String surname, String flightNumber,LocalDate flightDate)
    {
         return "Molo Air: Confirming flight "+ flightNumber+" booked for "+ name
                +" " + surname+ " on "+flightDate.toString()+".";
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

    public Customer getCustomer(Integer id) {
        return localApiClient
                .get()
                .uri("http://localhost:8201/customers/" + id)
                .retrieve()
                .bodyToMono(Customer.class)
                .block(REQUEST_TIMEOUT);
    }

    public Flight getFlight(Integer id) {
        return localApiClient
                .get()
                .uri("http://localhost:8202/flights/" + id)
                .retrieve()
                .bodyToMono(Flight.class)
                .block(REQUEST_TIMEOUT);
    }


    public List<Booking> searchBookings(BookingsSearchRequest searchRequest) {
        Map<SearchType, Supplier<Optional<List<Booking>>>>searchStrategies = new HashMap<>();

        searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findByCustomerID(searchRequest.getCustomerID()));
        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findByReferenceNumber(searchRequest.getReferenceNumber()));

        Optional<List<Booking>> bookingOptional = searchStrategies.get(searchRequest.getSearchType()).get();

        return bookingOptional.orElse(null);
    }

    public List<Booking> searchBookingsWithCustomer(BookingsIDSearchRequest customerID) {
//        Customer customer = getCustomer(customerID.getCustomerID());
//        if(customer==null)
//        {
//            return null;
//        }else{
            Map<SearchType, Supplier<Optional<List<Booking>>>>searchStrategies = new HashMap<>();
            searchStrategies.put(SearchType.CUSTOMER_ID_SEARCH, () -> bookingRepository.findByCustomerID(customerID.getCustomerID()));
            Optional<List<Booking>> bookingOptional = searchStrategies.get(SearchType.CUSTOMER_ID_SEARCH).get();

            return bookingOptional.orElse(null);
//        }

    }


    public List<Booking> searchBookingsWithRef(BookingsReferenceSearchRequest referenceNumber) {
        Map<SearchType, Supplier<Optional<List<Booking>>>>searchStrategies = new HashMap<>();
        searchStrategies.put(SearchType.REFERENCE_NUMBER_SEARCH, () -> bookingRepository.findByReferenceNumber(referenceNumber.getReferenceNumber()));
        Optional<List<Booking>> bookingOptional = searchStrategies.get(SearchType.REFERENCE_NUMBER_SEARCH).get();

        return bookingOptional.orElse(null);
    }
}
