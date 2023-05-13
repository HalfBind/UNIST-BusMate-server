package halfbind.UNISTBusMate.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import halfbind.UNISTBusMate.domain.Bookmark;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark, Long> {

    List<Bookmark> findByUserName(String userName);
}
