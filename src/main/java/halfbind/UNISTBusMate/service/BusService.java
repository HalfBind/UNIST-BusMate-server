package halfbind.UNISTBusMate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.repository.BusRepository;
import halfbind.UNISTBusMate.util.TimeManager;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;
    private Long busIdCounter = 0L;

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public Bus createBus(Bus bus) {
        bus.setId(busIdCounter++);
        System.out.println(bus.getId());
        System.out.println(bus.getDepartureTime());
        System.out.println(bus.getRouteNumber());
        System.out.println(bus.getRouteDirection());
        return busRepository.save(bus);
    }

    public List<Bus> getBusesByRoute(String routeNumber, String routeDirection) {
        return busRepository.findByRouteNumberAndRouteDirection(routeNumber, routeDirection);
    }

    public List<Bus> getBusesByRouteAndDepartureTime(String routeNumber, String routeDirection, String departureTime) {
        List<Bus> buses = getBusesByRoute(routeNumber, routeDirection);
        return buses
            .stream()
            .filter(bus -> TimeManager.isAfter(bus.getDepartureTime(), departureTime))
            .toList();
    }
}
