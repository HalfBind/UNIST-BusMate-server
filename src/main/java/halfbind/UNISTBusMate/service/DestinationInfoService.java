package halfbind.UNISTBusMate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.DestinationInfo;
import halfbind.UNISTBusMate.repository.DestinationInfoRepository;

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
}
