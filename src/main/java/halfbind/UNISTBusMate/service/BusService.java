package halfbind.UNISTBusMate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.repository.BusRepository;
import halfbind.UNISTBusMate.util.TimeManager;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private DestinationInfoService destinationInfoService;
    private Long idCounter = 0L;

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public Bus createBus(Bus bus) {
        bus.setId(idCounter++);
        bus.getDestinationInfos().forEach(destinationInfoService::createDestinationInfo);
        return busRepository.save(bus);
    }

    public List<Bus> findByRouteNumber(String routeNumber) {
        return busRepository.findByRouteNumber(routeNumber);
    }

    public List<Bus> findByRouteNumberWithDepartureTime(String routeNumber, String departureTime) {
        List<Bus> buses = findByRouteNumber(routeNumber);
        return buses
            .stream()
            .filter(bus -> TimeManager.isAfter(bus.getDepartureTime(), departureTime))
            .toList();
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

    public Bus findById(Long id) {
        return busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus is not exist"));
    }
}
