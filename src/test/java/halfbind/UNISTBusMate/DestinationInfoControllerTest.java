package halfbind.UNISTBusMate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import halfbind.UNISTBusMate.controller.DestinationInfoController;
import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;
import halfbind.UNISTBusMate.service.DestinationInfoService;

@RunWith(SpringRunner.class)
@WebMvcTest(DestinationInfoController.class)
public class DestinationInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationInfoService destinationInfoService;

    @Test
    public void testGetDestinationInfoByDestinationName() throws Exception {
        List<DestinationInfo> destinationInfos = new ArrayList<>();

        Bus bus = new Bus();
        bus.setRouteNumber("123");
        bus.setDepartureTime("12:00");
        bus.setId(1L);
        bus.setRouteDirection("direction");
        bus.setDestinationInfos(destinationInfos);

        DestinationInfo destinationInfo = new DestinationInfo();
        destinationInfo.setId(1L);
        destinationInfo.setDestination(Destination.GUYEONG_RI);
        destinationInfo.setBus(bus);
        destinationInfos.add(destinationInfo);

        when(destinationInfoService.findByDestinationName("GUYEONG_RI")).thenReturn(destinationInfos);

        mockMvc.perform(get("/destinationInfos/GUYEONG_RI").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].destination").value(Destination.GUYEONG_RI.toString()));
    }

    @Test
    public void testGetDestinationInfoByDestinationNameWithDepartureTime() throws Exception {
        List<DestinationInfo> destinationInfos = new ArrayList<>();

        Bus bus = new Bus();
        bus.setRouteNumber("123");
        bus.setDepartureTime("09:00");
        bus.setId(1L);
        bus.setRouteDirection("direction");
        bus.setDestinationInfos(destinationInfos);

        DestinationInfo destinationInfo = new DestinationInfo();
        destinationInfo.setId(1L);
        destinationInfo.setDestination(Destination.SEONGNAM);
        destinationInfos.add(destinationInfo);
        destinationInfo.setBus(bus);

        when(destinationInfoService.findByDestinationNameWithDepartureTime("SEONGNAM", "09:00")).thenReturn(
            destinationInfos);

        mockMvc.perform(
                get("/destinationInfos/SEONGNAM").param("departureTime", "09:00").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].destination").value(Destination.SEONGNAM.toString()))
            .andExpect(jsonPath("$[0].departureTime").value("09:00"));
    }

    @Test
    public void testGetDestinationInfosByArrivalTime() throws Exception {
        List<DestinationInfo> destinationInfos = new ArrayList<>();

        Bus bus = new Bus();
        bus.setRouteNumber("123");
        bus.setDepartureTime("11:00");
        bus.setId(1L);
        bus.setRouteDirection("direction");
        bus.setDestinationInfos(destinationInfos);

        DestinationInfo destinationInfo = new DestinationInfo();
        destinationInfo.setId(1L);
        destinationInfo.setDestination(Destination.SAMSAN);
        destinationInfo.setBus(bus);
        destinationInfos.add(destinationInfo);

        when(destinationInfoService.findByDestinationNameAndArrivalTime("SAMSAN", "12:00")).thenReturn(
            destinationInfos);

        mockMvc.perform(get("/destinationInfos/SAMSAN/until/12:00"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].destination").value(Destination.SAMSAN.toString()));
    }

    @Test
    public void testGetDestinationInfosByArrivalTimeWithDepartureTime() throws Exception {
        List<DestinationInfo> destinationInfos = new ArrayList<>();

        Bus bus = new Bus();
        bus.setRouteNumber("123");
        bus.setDepartureTime("11:00");
        bus.setId(1L);
        bus.setRouteDirection("direction");
        bus.setDestinationInfos(destinationInfos);

        DestinationInfo destinationInfo = new DestinationInfo();
        destinationInfo.setId(1L);
        destinationInfo.setDestination(Destination.SAMSAN);
        destinationInfo.setBus(bus);
        destinationInfos.add(destinationInfo);

        when(destinationInfoService.findByDestinationNameAndArrivalTimeWithDepartureTime("SAMSAN", "12:00", "11:00")).thenReturn(
            destinationInfos);

        mockMvc.perform(get("/destinationInfos/SAMSAN/until/12:00").param("departureTime", "11:00")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].destination").value(Destination.SAMSAN.toString()));
    }
}