package halfbind.UNISTBusMate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import halfbind.UNISTBusMate.domain.Destination;
@RunWith(SpringRunner.class)
public class DestinationTest {

    @Test
    public void testGet() {
        assertEquals(Destination.KTX_ULSAN_STATION, Destination.get("KTX_ULSAN_STATION"));
        assertEquals(Destination.SINBOK_ROTARY, Destination.get("SINBOK_ROTARY"));
        assertEquals(Destination.EONYANG_TERMINAL, Destination.get("EONYANG_TERMINAL"));
        assertEquals(Destination.ULSAN_TERMINAL, Destination.get("ULSAN_TERMINAL"));
        assertEquals(Destination.ULSAN_UNIVERSITY, Destination.get("ULSAN_UNIVERSITY"));
        assertEquals(Destination.GUYEONG_RI, Destination.get("GUYEONG_RI"));
        assertEquals(Destination.SEONGNAM, Destination.get("SEONGNAM"));
        assertEquals(Destination.SAMSAN, Destination.get("SAMSAN"));
        assertNull(Destination.get("invalid_destination"));
    }
}

