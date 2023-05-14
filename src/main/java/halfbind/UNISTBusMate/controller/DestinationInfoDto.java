package halfbind.UNISTBusMate.controller;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;
import lombok.Data;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class DestinationInfoDto {
    private String routeNumber;
    private String routeDirection;
    private String departureTime;
    private Destination destination;
    private String arrivalTime;

    public DestinationInfoDto(DestinationInfo destinationInfo) {
        this.routeNumber = destinationInfo.getBus().getRouteNumber();
        this.routeDirection = destinationInfo.getBus().getRouteDirection();
        this.departureTime = destinationInfo.getBus().getDepartureTime();
        this.destination = destinationInfo.getDestination();
        this.arrivalTime = destinationInfo.getArrivalTime();
    }
}
