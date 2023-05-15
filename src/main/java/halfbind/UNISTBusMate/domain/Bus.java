package halfbind.UNISTBusMate.domain;

import java.util.Arrays;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "buses")
@NoArgsConstructor
public class Bus {
    @Id
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

    public List<DestinationInfo> getDestinationInfos() {
        return destinationInfos;
    }

    public String getRouteDirection() {
        return routeDirection;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public void setRouteDirection(String routeDirection) {
        this.routeDirection = routeDirection;
    }

    public void setDestinationInfos(List<DestinationInfo> destinationInfos) {
        this.destinationInfos = destinationInfos;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
