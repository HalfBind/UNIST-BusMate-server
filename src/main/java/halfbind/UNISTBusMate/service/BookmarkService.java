package halfbind.UNISTBusMate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halfbind.UNISTBusMate.controller.BookmarkRequestDto;
import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.domain.Bus;
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
        Bus bus = busService.findById(bookmarkRequestDto.getBusId());
        bookmark.setBus(bus);
        bookmark.setTimeOffset(bookmarkRequestDto.getTimeOffset());
        bookmark.setDays(bookmarkRequestDto.getDays());
        bookmarkRepository.save(bookmark);
        return bookmark;
    }

    public Bookmark findById(Long id) {
        return bookmarkRepository.findById(id).orElseThrow(() -> new RuntimeException("Bookmark is not exist"));
    }

    public void delete(Bookmark bookmark) {
        bookmarkRepository.delete(bookmark);
    }
}
