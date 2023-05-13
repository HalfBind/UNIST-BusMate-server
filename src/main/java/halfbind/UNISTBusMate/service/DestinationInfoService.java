package halfbind.UNISTBusMate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.DestinationInfo;
import halfbind.UNISTBusMate.repository.BusRepository;
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
}
