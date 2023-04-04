package cse364.project.domain;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import lombok.Getter;

@Entity
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
