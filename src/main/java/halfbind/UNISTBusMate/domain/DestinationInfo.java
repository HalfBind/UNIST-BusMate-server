package halfbind.UNISTBusMate.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import halfbind.UNISTBusMate.util.TimeManager;
import lombok.NoArgsConstructor;

@Document(collection = "destinationInfos")
@NoArgsConstructor
public class DestinationInfo {

    public DestinationInfo(Destination destination, String departureTime) {
        if (destination == Destination.KTX_ULSAN_STATION) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 15L);
        } else if (destination == Destination.SINBOK_ROTARY) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 23L);
        } else if (destination == Destination.EONYANG_TERMINAL) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 22L);
        } else if (destination == Destination.ULSAN_TERMINAL) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 47L);
        } else if (destination == Destination.ULSAN_UNIVERSITY) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 27L);
        } else if (destination == Destination.GUYEONG_RI) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 10L);
        } else if (destination == Destination.SEONGNAM) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 40L);
        } else if (destination == Destination.SAMSAN) {
            this.arrivalTime = TimeManager.addMinutes(departureTime, 50L);
        }
        this.destination = destination;
    }

    @Id
    private Long id;
    private Destination destination;
    private String arrivalTime;
    @DBRef
    private Bus bus;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Destination getDestination() {
        return destination;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
