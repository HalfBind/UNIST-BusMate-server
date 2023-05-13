package halfbind.UNISTBusMate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;
import halfbind.UNISTBusMate.repository.DestinationInfoRepository;
import halfbind.UNISTBusMate.util.TimeManager;

@Service
public class DestinationInfoService {

    @Autowired
    private DestinationInfoRepository destinationInfoRepository;

    private Long idCounter = 0L;

    public DestinationInfo createDestinationInfo(DestinationInfo destinationInfo) {
        destinationInfo.setId(idCounter++);
        return destinationInfoRepository.save(destinationInfo);
    }

    public List<DestinationInfo> findByDestinationName(String destinationName) {
        Destination destination = Destination.get(destinationName);
        return destinationInfoRepository.findByDestination(destination);
    }

    public List<DestinationInfo> findByDestinationNameWithDepartureTime(String destinationName, String departureTime) {
        List<DestinationInfo> destinationInfos = findByDestinationName(destinationName);
        return destinationInfos
            .stream()
            .filter(
                destinationInfo -> TimeManager.isAfter(destinationInfo.getBus().getDepartureTime(), departureTime))
            .toList();
    }

    public List<DestinationInfo> findByDestinationNameAndArrivalTime(String destinationName, String arrivalTime) {
        List<DestinationInfo> destinationInfos = findByDestinationName(destinationName);
        return destinationInfos
            .stream()
            .filter(
                destinationInfo -> TimeManager.isBefore(destinationInfo.getArrivalTime(), arrivalTime))
            .toList();
    }

    public List<DestinationInfo> findByDestinationNameAndArrivalTimeWithDepartureTime(String destinationName,
        String arrivalTime, String departureTime) {
        List<DestinationInfo> destinationInfos = findByDestinationNameAndArrivalTime(destinationName, arrivalTime);
        return destinationInfos
            .stream()
            .filter(
                destinationInfo -> TimeManager.isAfter(destinationInfo.getBus().getDepartureTime(), departureTime))
            .toList();
    }
}
