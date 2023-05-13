package halfbind.UNISTBusMate.controller;

import java.util.List;

import halfbind.UNISTBusMate.domain.Day;
import lombok.Data;

@Data
public class BookmarkRequestDto {
    private Long busId;
    private String userName;
    private List<Day> days;
}
