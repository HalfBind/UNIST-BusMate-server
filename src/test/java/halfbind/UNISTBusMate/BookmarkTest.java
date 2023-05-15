// package halfbind.UNISTBusMate;
//
// import org.junit.Test;
//
// import java.util.Arrays;
// import java.util.List;
//
// import static org.junit.Assert.assertEquals;
//
// import halfbind.UNISTBusMate.domain.Bookmark;
// import halfbind.UNISTBusMate.domain.Destination;
// import halfbind.UNISTBusMate.domain.Day;
//
// public class BookmarkTest {
//
//     @Test
//     public void testBookmark() {
//         Bookmark bookmark = new Bookmark();
//
//         bookmark.setDestination(Destination.KTX_ULSAN_STATION);
//         bookmark.setArrivalTime("10:30");
//         bookmark.setUserName("John Doe");
//         Object Day;
//         bookmark.setDays(Arrays.asList(halfbind.UNISTBusMate.domain.Day.MON));
//
//         assertEquals(bookmark.getDestination(), Destination.KTX_ULSAN_STATION);
//         assertEquals(bookmark.getArrivalTime(), "10:30");
//         assertEquals(bookmark.getUserName(), "John Doe");
//         assertEquals(bookmark.getDays(), Arrays.asList(halfbind.UNISTBusMate.domain.Day.MON));
//     }
// }
//
