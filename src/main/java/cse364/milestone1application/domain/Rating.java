package cse364.milestone1application.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
public class Rating {
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
