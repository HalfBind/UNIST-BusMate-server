package halfbind.UNISTBusMate.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import halfbind.UNISTBusMate.util.TimeManager;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "destinationInfos")
@Data
@NoArgsConstructor
public class DestinationInfo {

    public DestinationInfo(Destination destination, String departureTime) {
        switch (destination) {
            case KTX_ULSAN_STATION -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 15L);
            }
            case SINBOK_ROTARY -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 23L);
            }
            case EONYANG_TERMINAL -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 22L);
            }
            case ULSAN_TERMINAL -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 47L);
            }
            case ULSAN_UNIVERSITY -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 27L);
            }
            case GUYEONG_RI -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 10L);
            }
            case SEONGNAM -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 40L);
            }
            case SAMSAN -> {
                this.arrivalTime = TimeManager.addMinutes(departureTime, 50L);
            }
        }

        this.destination = destination;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Destination destination;
    private String arrivalTime;
}
