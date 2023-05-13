package halfbind.UNISTBusMate.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import halfbind.UNISTBusMate.domain.Bus;

@Repository
public interface BusRepository extends MongoRepository<Bus, Long> {
    List<Bus> findByRouteNumberAndRouteDirection(String routeNumber, String routeDirection);
}
