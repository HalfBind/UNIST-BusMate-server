package halfbind.UNISTBusMate.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.domain.Day;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookmarkResponseDto {
    private Long id;
    private Long timeOffset;
    private String userName;
    private List<Day> days;
    private BusDto busInfo;

    public BookmarkResponseDto(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.timeOffset = bookmark.getTimeOffset();
        this.userName = bookmark.getUserName();
        this.days = bookmark.getDays();
        this.busInfo = new BusDto(bookmark.getBus());
    }
}
