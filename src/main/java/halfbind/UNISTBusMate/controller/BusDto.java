package halfbind.UNISTBusMate.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BusDto {
    private String routeNumber;
    private String routeDirection;
    private String departureTime;
    private List<Destination> destinations;

    public BusDto(Bus bus) {
        this.routeNumber = bus.getRouteNumber();
        this.routeDirection = bus.getRouteDirection();
        this.departureTime = bus.getDepartureTime();
        this.destinations = bus
            .getDestinationInfos()
            .stream()
            .map(DestinationInfo::getDestination)
            .toList();
    }
}
