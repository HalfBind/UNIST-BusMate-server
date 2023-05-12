package halfbind.UNISTBusMate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.service.BusService;

@RestController
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        Bus bus = busService.getBusById(id);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
