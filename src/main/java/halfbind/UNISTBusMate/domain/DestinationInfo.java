package halfbind.UNISTBusMate.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DestinationInfo {
    @Id
    @GeneratedValue
    private Long id;
    private Destination destination;
    private String arrivalTime;
}
