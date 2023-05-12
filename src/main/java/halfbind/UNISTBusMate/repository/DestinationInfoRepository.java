package halfbind.UNISTBusMate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import halfbind.UNISTBusMate.domain.DestinationInfo;

@Repository
public interface DestinationInfoRepository extends MongoRepository<DestinationInfo, Long> {
}
