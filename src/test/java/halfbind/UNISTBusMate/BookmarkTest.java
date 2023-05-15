package halfbind.UNISTBusMate;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import halfbind.UNISTBusMate.domain.Bookmark;
import halfbind.UNISTBusMate.domain.Bus;
import halfbind.UNISTBusMate.domain.Destination;
import halfbind.UNISTBusMate.domain.Day;

public class BookmarkTest {

    @Test
    public void testBookmark() {
        Bookmark bookmark = new Bookmark();

        Bus bus = new Bus();
        bus.setId(1L);
        bookmark.setBus(bus);
        bookmark.setUserName("Zelda");
        Object Day;
        bookmark.setDays(Arrays.asList(new Day[] {halfbind.UNISTBusMate.domain.Day.MON}));

        assertEquals(bookmark.getBus(), bus);
        assertEquals(bookmark.getUserName(), "Zelda");
        assertEquals(bookmark.getDays(), Arrays.asList(halfbind.UNISTBusMate.domain.Day.MON));
    }
}

