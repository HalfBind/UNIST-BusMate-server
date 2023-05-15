package halfbind.UNISTBusMate.domain;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;

@Document(collection = "bookmarks")
@NoArgsConstructor
public class Bookmark {
    @Id
    private Long id;
    @DBRef
    private Bus bus;
    private String userName;
    private List<Day> days;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
