package halfbind.UNISTBusMate.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "destinationInfos")
public class DestinationInfo {
    @Id
    @GeneratedValue
    private Long id;
    private Destination destination;
    private String arrivalTime;
}
