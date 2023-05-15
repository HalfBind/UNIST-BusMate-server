package halfbind.UNISTBusMate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import halfbind.UNISTBusMate.controller.BookmarkController;
import halfbind.UNISTBusMate.controller.BookmarkRequestDto;
import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Day;
import halfbind.UNISTBusMate.service.BookmarkService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookmarkController.class)
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookmarkService bookmarkService;

    @Test
    public void testGetBookmarksByUserName() throws Exception {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setRouteNumber("123");
        bus.setRouteDirection("direction");
        bus.setDepartureTime("12:00");
        bus.setDestinationInfos(new ArrayList<>());
        Bookmark bookmark = new Bookmark();
        bookmark.setBus(bus);
        bookmark.setUserName("Gibeom");
        bookmark.setDays(List.of(new Day[] {Day.SUN}));

        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(bookmark);

        when(bookmarkService.findByUserName("Gibeom")).thenReturn(bookmarks);

        mockMvc.perform(get("/bookmarks/Gibeom").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].userName").value("Gibeom"));
    }

    @Test
    public void testGetBookmarksByUserNameNotFound() throws Exception {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserName("delphox");
        bookmark.setDays(List.of(new Day[] {Day.SUN}));

        System.out.println(bookmark);
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(bookmark);

        when(bookmarkService.findByUserName("delphox")).thenReturn(bookmarks);

        mockMvc.perform(get("/bookmarks/Gibeom").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateBookmark() throws Exception {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setRouteNumber("123");
        bus.setRouteDirection("direction");
        bus.setDepartureTime("12:00");
        bus.setDestinationInfos(new ArrayList<>());

        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto();
        bookmarkRequestDto.setUserName("delphox");
        bookmarkRequestDto.setBusId(1L);
        bookmarkRequestDto.setDays(List.of(new Day[] {Day.SUN}));

        Bookmark bookmark = new Bookmark();
        bookmark.setId(1L);
        bookmark.setBus(bus);
        bookmark.setUserName("delphox");
        bookmark.setDays(List.of(new Day[] {Day.SUN}));

        when(bookmarkService.createBookmark(any(BookmarkRequestDto.class))).thenReturn(bookmark);

        mockMvc.perform(
                post("/bookmarks").content(asJsonString(bookmarkRequestDto)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void testCreateBookmarkNotFound() throws Exception {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setRouteNumber("123");
        bus.setRouteDirection("direction");
        bus.setDepartureTime("12:00");
        bus.setDestinationInfos(new ArrayList<>());

        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto();
        bookmarkRequestDto.setUserName("delphox");
        bookmarkRequestDto.setBusId(11L);
        bookmarkRequestDto.setDays(List.of(new Day[] {Day.SUN}));

        Bookmark bookmark = new Bookmark();
        bookmark.setId(1L);
        bookmark.setBus(bus);
        bookmark.setUserName("delphox");
        bookmark.setDays(List.of(new Day[] {Day.SUN}));

        when(bookmarkService.createBookmark(any(BookmarkRequestDto.class))).thenThrow(new RuntimeException());

        mockMvc.perform(
                post("/bookmarks").content(asJsonString(bookmarkRequestDto)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBookmark() throws Exception {
        Bus bus = new Bus();
        bus.setId(1L);
        bus.setRouteNumber("123");
        bus.setRouteDirection("direction");
        bus.setDepartureTime("12:00");
        bus.setDestinationInfos(new ArrayList<>());

        BookmarkRequestDto bookmarkRequestDto = new BookmarkRequestDto();
        bookmarkRequestDto.setUserName("delphox");
        bookmarkRequestDto.setBusId(1L);
        bookmarkRequestDto.setDays(List.of(new Day[] {Day.SUN}));

        Bookmark bookmark = new Bookmark();
        bookmark.setId(1L);
        bookmark.setBus(bus);
        bookmark.setUserName("delphox");
        bookmark.setDays(List.of(new Day[] {Day.SUN}));
        mockMvc.perform(delete("/bookmarks/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteBookmarkNotFound() throws Exception {

        when(bookmarkService.findById(any(Long.class))).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/bookmarks/1123").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
