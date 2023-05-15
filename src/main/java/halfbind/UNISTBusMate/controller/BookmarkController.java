package halfbind.UNISTBusMate.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.service.BookmarkService;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/{userName}")
    public ResponseEntity<List<BookmarkResponseDto>> getBookmarksByUserName(@PathVariable String userName) {
        List<Bookmark> bookmarks = bookmarkService.findByUserName(userName);
        List<BookmarkResponseDto> result = bookmarks
            .stream()
            .map(BookmarkResponseDto::new)
            .toList();
        if (result.size() > 0) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<BookmarkResponseDto> createBookmark(@RequestBody BookmarkRequestDto bookmarkRequestDto) {
        Bookmark bookmark;
        try {
            bookmark = bookmarkService.createBookmark(bookmarkRequestDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        Long id = bookmark.getId();
        BookmarkResponseDto bookmarkResponseDto = new BookmarkResponseDto(bookmark);
        return ResponseEntity.created(URI.create("/bookmarks/" + id)).body(bookmarkResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookmark(@PathVariable Long id) {
        Bookmark bookmark;
        try {
            bookmark = bookmarkService.findById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        bookmarkService.delete(bookmark);
        return ResponseEntity.noContent().build();
    }
}
