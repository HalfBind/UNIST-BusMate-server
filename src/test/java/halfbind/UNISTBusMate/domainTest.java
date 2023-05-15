package halfbind.UNISTBusMate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;

@RunWith(SpringRunner.class)
public class domainTest {

    @Test
    public void constructDestinationAndDestinationInfoTest() {
        Destination destination;
        DestinationInfo destinationInfo;
        destination = Destination.get("KTX_ULSAN_STATION");
        assertEquals(destination, Destination.KTX_ULSAN_STATION);
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("SINBOK_ROTARY");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("EONYANG_TERMINAL");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("ULSAN_TERMINAL");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("ULSAN_UNIVERSITY");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("GUYEONG_RI");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("SEONGNAM");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("SAMSAN");
        destinationInfo = new DestinationInfo(destination, "12:00");
        destination = Destination.get("notexistdestination");
    }

}
