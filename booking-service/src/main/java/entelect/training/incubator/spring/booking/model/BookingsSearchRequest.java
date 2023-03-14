package entelect.training.incubator.spring.booking.model;

import lombok.Data;

@Data
public class BookingsSearchRequest {
    private SearchType searchType;
    private Integer customerID;
    private String referenceNumber;
}
