package entelect.training.incubator.spring.booking.repository;

import entelect.training.incubator.spring.booking.model.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookingRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void whenFindByCustomerID_thenReturnBooking() {
        Booking booking = createTestBooking(3,1,"ABC1234");
        entityManager.persistAndFlush(booking);

        Optional<Booking> found = bookingRepository.findByCustomerID(booking.getCustomerID());
        assertThat(found).isPresent();
        assertThat(found.get().getCustomerID()).isEqualTo(booking.getCustomerID());
    }

    @Test
    public void whenFindByReferenceNumber_thenReturnBooking() {
        Booking booking = createTestBooking(3,1,"ABC1234");
        entityManager.persistAndFlush(booking);

        Optional<Booking> found = bookingRepository.findByReferenceNumber(booking.getReferenceNumber());
        assertThat(found).isPresent();
        assertThat(found.get().getReferenceNumber()).isEqualTo(booking.getReferenceNumber());
    }



    private Booking createTestBooking(Integer customerID, Integer flightID, String referenceNumber) {
        Booking booking = new Booking();
        booking.setCustomerID(customerID);
        booking.setFlightID(flightID);
        booking.setReferenceNumber(referenceNumber);
        return booking;
    }

}
