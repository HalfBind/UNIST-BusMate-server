package halfbind.UNISTBusMate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.repository.BusRepository;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus getBusById(Long id) {
        return busRepository.findById(id).orElse(null);
    }
}
