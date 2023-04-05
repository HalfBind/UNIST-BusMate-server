package cse364.milestone1application.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private Long userId;
    private Long movieId;
    private Double rating;
    private String timestamp;
}
