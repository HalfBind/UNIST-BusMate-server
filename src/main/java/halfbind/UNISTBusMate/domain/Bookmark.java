package halfbind.UNISTBusMate.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bookmarks")
@Data
@NoArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue
    private Long id;
    @DBRef
    private Bus bus;
    private String userName;
    private List<Day> days;
}
