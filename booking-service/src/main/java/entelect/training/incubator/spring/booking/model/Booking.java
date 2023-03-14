package entelect.training.incubator.spring.booking.model;

import entelect.training.incubator.spring.customer.model.Customer;
import entelect.training.incubator.spring.flight.model.Flight;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private Customer customerID;
//    private Flight flightID;
    private Integer customerID;
    private Integer flightID;
    private String referenceNumber;

}
