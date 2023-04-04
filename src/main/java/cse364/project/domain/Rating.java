package cse364.project.domain;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

public class Rating {
    @Id
    private Integer id;

    private User user;
    private Movie movie;
    private Double rating;
    private Timestamp timestamp;
}
