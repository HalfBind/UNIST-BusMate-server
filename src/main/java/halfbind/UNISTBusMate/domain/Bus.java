package halfbind.UNISTBusMate.domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "buses")
@Data
@NoArgsConstructor
public class Bus {
    @Id
    @GeneratedValue
    private Long id;

    private String routeNumber;
    private String routeDirection;
    private String departureTime;
    @DBRef
    private List<DestinationInfo> destinationInfos;

    public Bus(String routeNumber, String routeDirection, String departureTime, List<DestinationInfo> destinationInfos) {
        this.routeNumber = routeNumber;
        this.routeDirection = routeDirection;
        this.departureTime = departureTime;
        this.destinationInfos = destinationInfos;
    }
}
