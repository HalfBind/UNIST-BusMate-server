package cse364.milestone1application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Rating {
    @Id
    @Getter
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long movieId;
    private Double rating;
    private String timestamp;

    public Rating(Long userId, Long movieId, Double ratingScore, String timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = ratingScore;
        this.timestamp = timestamp;
    }
}
