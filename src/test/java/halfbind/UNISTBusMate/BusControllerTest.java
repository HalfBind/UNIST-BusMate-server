package halfbind.UNISTBusMate;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import halfbind.UNISTBusMate.controller.BusController;
import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.service.BusService;

@RunWith(SpringRunner.class)
@WebMvcTest(BusController.class)
public class BusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusService busService;

    @Test
    public void testGetBusesByRouteNumber() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));
        buses.add(new Bus("123", "direction2", "11:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumber("123")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeDirection", Matchers.is("direction1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].routeDirection", Matchers.is("direction2")));
    }

    @Test
    public void testGetBusesByRouteNumberNotFound() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));
        buses.add(new Bus("123", "direction2", "11:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumber("123")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/1223"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetBusesByRouteNumberAndDirection() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));
        buses.add(new Bus("123", "direction1", "11:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberAndDirection("123", "direction1")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123/direction1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeDirection", Matchers.is("direction1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[1].routeDirection", Matchers.is("direction1")));
    }

    @Test
    public void testGetBusesByRouteNumberAndDirectionNotFound() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));
        buses.add(new Bus("123", "direction1", "11:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberAndDirection("123", "direction1")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123/direction3"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @Test
    public void testGetBusesByRouteNumberWithDepartureTime() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberWithDepartureTime("123", "10:00")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123?departureTime=10:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeDirection", Matchers.is("direction1")));
    }

    @Test
    public void testGetBusesByRouteNumberWithDepartureTimeNotFound() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberWithDepartureTime("123", "10:00")).thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/1213?departureTime=10:00"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetBusesByRouteNumberAndDirectionWithDepartureTime() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberAndDirectionWithDepartureTime("123", "direction1", "10:00"))
            .thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123/direction1?departureTime=10:00"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeNumber", Matchers.is("123")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].routeDirection", Matchers.is("direction1")));
    }

    @Test
    public void testGetBusesByRouteNumberAndDirectionWithDepartureTimeNotFound() throws Exception {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("123", "direction1", "10:00", new ArrayList<>()));

        Mockito.when(busService.findByRouteNumberAndDirectionWithDepartureTime("123", "direction1", "10:00"))
            .thenReturn(buses);

        mockMvc.perform(MockMvcRequestBuilders.get("/buses/123/direction11?departureTime=10:00"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
