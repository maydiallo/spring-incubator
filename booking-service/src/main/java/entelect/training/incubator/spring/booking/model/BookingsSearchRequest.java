package entelect.training.incubator.spring.booking.model;

import entelect.training.incubator.spring.customer.model.SearchType;
import lombok.Data;

@Data
public class BookingsSearchRequest {
    private SearchType searchType;
    private Integer customerID;
    private String referenceNumber;
}
