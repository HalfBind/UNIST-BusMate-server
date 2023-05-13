package halfbind.UNISTBusMate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    // @PostMapping("")
    // public ResponseEntity<BookmarkDto> createBookmark(@RequestBody BookmarkDto bookmarkDto) {
    //     bookmarkService.createBookmark(bookmarkDto);
    // }
    //
    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteBookmark(@PathVariable Long bookmarkId) {
    //     Optional<Bookmark> bookmarkOptional = .findById(bookmarkId);
    //     if (bookmarkOptional.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     Bookmark bookmark = bookmarkOptional.get();
    //     bookmarkService.delete(bookmark);
    //     return ResponseEntity.noContent().build();
    // }
}
