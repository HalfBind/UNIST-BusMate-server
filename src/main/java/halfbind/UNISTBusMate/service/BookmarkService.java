package halfbind.UNISTBusMate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.controller.BookmarkRequestDto;
import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.repository.BookmarkRepository;
import halfbind.UNISTBusMate.repository.DestinationInfoRepository;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private BusService busService;

    private static Long idCounter = 0L;

    public List<Bookmark> findByUserName(String userName) {
        return bookmarkRepository.findByUserName(userName);
    }

    public Bookmark createBookmark(BookmarkRequestDto bookmarkRequestDto) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(idCounter++);
        bookmark.setUserName(bookmarkRequestDto.getUserName());
        bookmark.setBus(busService.findById(bookmarkRequestDto.getBusId()));
        bookmark.setDays(bookmarkRequestDto.getDays());
        bookmarkRepository.save(bookmark);
        return bookmark;
    }
}
