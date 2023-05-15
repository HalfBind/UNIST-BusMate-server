package halfbind.UNISTBusMate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;

@RunWith(SpringRunner.class)
class DestinationInfoTest {

    @Test
    void testKTXUlsanStationArrivalTime() {
        DestinationInfo info = new DestinationInfo(Destination.KTX_ULSAN_STATION, "09:00");
        assertEquals("09:00", info.getArrivalTime());
    }

    @Test
    void testSinbokRotaryArrivalTime() {
        DestinationInfo info = new DestinationInfo(Destination.SINBOK_ROTARY, "10:00");
        assertEquals("10:00", info.getArrivalTime());
    }

    // Add more test cases for other destinations

    @Test
    void testInvalidDepartureTime() {
        DestinationInfo info = new DestinationInfo(Destination.KTX_ULSAN_STATION, "21:00");
        assertEquals("21:00", info.getArrivalTime());
    }
}
