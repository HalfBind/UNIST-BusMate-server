package halfbind.UNISTBusMate.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import halfbind.UNISTBusMate.domain.DestinationInfo;
import halfbind.UNISTBusMate.service.DestinationInfoService;

@RestController
@RequestMapping("/destinationInfos")
public class DestinationInfoController {

    @Autowired
    private DestinationInfoService destinationInfoService;

    @GetMapping("/{destinationName}")
    public ResponseEntity<List<DestinationInfoDto>> getDestinationInfoByDestinationName(
        @PathVariable String destinationName, @RequestParam(required = false) String departureTime) {

        List<DestinationInfo> destinationInfos;

        if (departureTime != null) {
            destinationInfos = destinationInfoService
                .findByDestinationNameWithDepartureTime(destinationName, departureTime);
        } else {
            destinationInfos = destinationInfoService.findByDestinationName(destinationName);
        }

        List<DestinationInfoDto> result = destinationInfos
            .stream()
            .map(DestinationInfoDto::new)
            .toList();
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
