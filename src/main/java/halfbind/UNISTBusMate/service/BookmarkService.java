package halfbind.UNISTBusMate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.repository.BookmarkRepository;
import halfbind.UNISTBusMate.repository.DestinationInfoRepository;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<Bookmark> findByUserName(String userName) {
        return bookmarkRepository.findByUserName(userName);
    }
}
