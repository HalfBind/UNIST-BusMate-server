package cse364.project.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

@Entity
public class Rating {
    @Id
    private Integer id;

    private User user;
    private Movie movie;
    private Double rating;
    private Timestamp timestamp;
}
