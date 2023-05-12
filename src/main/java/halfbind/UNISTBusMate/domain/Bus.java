package halfbind.UNISTBusMate.domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buses")
public class Bus {
    @Id
    @GeneratedValue
    private Long id;

    private String routeNumber;
    private String routeDirection;
    private String departureTime;
    private List<DestinationInfo> destinationInfos;
}
