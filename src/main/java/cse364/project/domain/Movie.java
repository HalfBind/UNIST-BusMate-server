package cse364.project.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document
public class Movie {
    @Id
    private Integer id;

    @Getter
    private String title;
    @Getter
    private String genre;

    public Movie(Integer id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }
}
