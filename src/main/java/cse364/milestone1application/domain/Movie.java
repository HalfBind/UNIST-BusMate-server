package cse364.milestone1application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document
@AllArgsConstructor
public class Movie {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String genre;

    public static Long idCounter = 0L;

    public Movie() {
        this.id = ++idCounter;
    }
}
