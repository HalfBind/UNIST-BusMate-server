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
        return busRepository.save(bus);
    }

    public List<Bus> findByRouteNumberAndDirection(String routeNumber, String routeDirection) {
        return busRepository.findByRouteNumberAndRouteDirection(routeNumber, routeDirection);
    }

    public List<Bus> findByRouteNumberAndDirectionWithDepartureTime(String routeNumber, String routeDirection, String departureTime) {
        List<Bus> buses = findByRouteNumberAndDirection(routeNumber, routeDirection);
        return buses
            .stream()
            .filter(bus -> TimeManager.isAfter(bus.getDepartureTime(), departureTime))
            .toList();
    }
}
