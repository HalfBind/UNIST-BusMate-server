// package halfbind.UNISTBusMate;
//
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import halfbind.UNISTBusMate.controller.BookmarkController;
// import halfbind.UNISTBusMate.controller.BookmarkDto;
// import halfbind.UNISTBusMate.domain.Bookmark;
// import halfbind.UNISTBusMate.domain.Day;
// import halfbind.UNISTBusMate.domain.Destination;
// import halfbind.UNISTBusMate.service.BookmarkService;
//
// @RunWith(SpringRunner.class)
// @WebMvcTest(BookmarkController.class)
// public class BookmarkControllerTest {
//
//     @Autowired
//     private MockMvc mockMvc;
//
//     @MockBean
//     private BookmarkService bookmarkService;
//
//     @Test
//     public void testGetBookmarksByUserName() throws Exception {
//         Bookmark bookmark = new Bookmark();
//         bookmark.setUserName("delphox");
//         bookmark.setDays(List.of(new Day[] {Day.SUN}));
//
//         System.out.println(bookmark);
//         List<Bookmark> bookmarks = new ArrayList<>();
//         bookmarks.add(bookmark);
//
//         when(bookmarkService.findByUserName("delphox")).thenReturn(bookmarks);
//
//         mockMvc.perform(get("/bookmarks/delphox").contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$.length()").value(1))
//             .andExpect(jsonPath("$[0].userName").value("delphox"));
//     }
//
//     @Test
//     public void testGetBookmarksByUserNameNotFound() throws Exception {
//         Bookmark bookmark = new Bookmark();
//         bookmark.setUserName("delphox");
//         bookmark.setDays(List.of(new Day[] {Day.SUN}));
//         bookmark.setDestination(Destination.SEONGNAM);
//         bookmark.setArrivalTime("12:00");
//
//         System.out.println(bookmark);
//         List<Bookmark> bookmarks = new ArrayList<>();
//         bookmarks.add(bookmark);
//
//         when(bookmarkService.findByUserName("delphox")).thenReturn(bookmarks);
//
//         mockMvc.perform(get("/bookmarks/gibeom").contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isNotFound());
//     }
//
//     @Test
//     public void testCreateBookmark() throws Exception {
//         BookmarkDto bookmarkDto = new BookmarkDto();
//         bookmarkDto.setUserName("delphox");
//         bookmarkDto.setArrivalTime("11:20");
//         bookmarkDto.setDays(List.of(new Day[] {Day.SUN}));
//         bookmarkDto.setDestination(Destination.SEONGNAM);
//
//         Bookmark bookmark = new Bookmark();
//         bookmark.setUserName("delphox");
//         bookmark.setArrivalTime("11:20");
//         bookmark.setDays(List.of(new Day[] {Day.SUN}));
//         bookmark.setDestination(Destination.SEONGNAM);
//
//         when(bookmarkService.createBookmark(any(BookmarkDto.class))).thenReturn(bookmark);
//
//         mockMvc.perform(post("/bookmarks").content(asJsonString(bookmarkDto)).contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isCreated());
//     }
//
//     @Test
//     public void testDeleteBookmark() throws Exception {
//         Bookmark bookmark = new Bookmark();
//         bookmark.setId(1L);
//         bookmark.setUserName("delphox");
//         bookmark.setArrivalTime("11:20");
//         bookmark.setDays(List.of(new Day[] {Day.SUN}));
//         bookmark.setDestination(Destination.SEONGNAM);
//
//         BookmarkDto bookmarkDto = new BookmarkDto();
//         bookmarkDto.setUserName("delphox");
//         bookmarkDto.setArrivalTime("11:20");
//         bookmarkDto.setDays(List.of(new Day[] {Day.SUN}));
//         bookmarkDto.setDestination(Destination.SEONGNAM);
//
//         when(bookmarkService.createBookmark(any(BookmarkDto.class))).thenReturn(bookmark);
//
//         mockMvc.perform(post("/bookmarks").content(asJsonString(bookmarkDto)).contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isCreated());
//
//         mockMvc.perform(delete("/bookmarks/1").contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isNoContent());
//     }
//
//     @Test
//     public void testDeleteBookmarkNotFound() throws Exception {
//         mockMvc.perform(delete("/bookmarks/1123").contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isNotFound());
//     }
//
//
//     private static String asJsonString(final Object obj) {
//         try {
//             final ObjectMapper mapper = new ObjectMapper();
//             return mapper.writeValueAsString(obj);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
