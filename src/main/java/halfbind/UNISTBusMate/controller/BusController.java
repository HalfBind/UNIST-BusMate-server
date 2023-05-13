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

        buses.forEach(busService::createBus);

        System.out.println("Data initialization complete.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        Bus bus = busService.getBusById(id);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{routeNumber}/{routeDirection}")
    public ResponseEntity<List<Bus>> getBusesByRouteNumberAndDirection(@PathVariable String routeNumber,
        @PathVariable String routeDirection, @RequestParam(required = false) String departureTime) {
        List<Bus> buses;

        if (departureTime != null) {
            buses = busService.findByRouteNumberAndDirectionWithDepartureTime(routeNumber, routeDirection, departureTime);
        } else {
            buses = busService.findByRouteNumberAndDirection(routeNumber, routeDirection);
        }

        if (buses != null) {
            return ResponseEntity.ok(buses);
        }
        return ResponseEntity.notFound().build();
    }
}
