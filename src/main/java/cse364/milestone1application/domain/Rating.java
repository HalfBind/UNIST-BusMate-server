package cse364.milestone1application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@Data
public class Rating {
    @Id
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
