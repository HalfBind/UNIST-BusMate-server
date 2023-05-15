package halfbind.UNISTBusMate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.service.BusService;
import halfbind.UNISTBusMate.util.DataManager;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    public BusController(BusService busService) throws Exception {
        System.out.println("Data initializing...");
        this.busService = busService;
        List<Bus> buses = DataManager.getBusData();

        buses.forEach(busService::createBusForInit);

        System.out.println("Data initialization complete.");
    }

    @GetMapping("/{routeNumber}")
    public ResponseEntity<List<BusDto>> getBusesByRouteNumber(@PathVariable String routeNumber,
        @RequestParam(required = false) String departureTime) {
        List<Bus> buses;

        if (departureTime != null) {
            buses = busService.findByRouteNumberWithDepartureTime(routeNumber, departureTime);
        } else {
            buses = busService.findByRouteNumber(routeNumber);
        }

        List<BusDto> result = buses
            .stream()
            .map(BusDto::new)
            .toList();

        if (result.size() > 0) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{routeNumber}/{routeDirection}")
    public ResponseEntity<List<BusDto>> getBusesByRouteNumberAndDirection(@PathVariable String routeNumber,
        @PathVariable String routeDirection, @RequestParam(required = false) String departureTime) {
        List<Bus> buses;

        if (departureTime != null) {
            buses = busService.findByRouteNumberAndDirectionWithDepartureTime(routeNumber, routeDirection,
                departureTime);
        } else {
            buses = busService.findByRouteNumberAndDirection(routeNumber, routeDirection);
        }

        List<BusDto> result = buses
            .stream()
            .map(BusDto::new)
            .toList();

        if (result.size() > 0) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
