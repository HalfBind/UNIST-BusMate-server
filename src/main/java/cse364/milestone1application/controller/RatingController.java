package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Rating;
import cse364.milestone1application.repository.RatingRepository;
import cse364.milestone1application.util.Migrator;

@RestController
public class RatingController {
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) throws IOException {
        this.ratingRepository = ratingRepository;
        List<Rating> ratings = Migrator.getRatings();

        System.out.println("rating data migration progressing...");
        for (Rating rating : ratings) {
            this.ratingRepository.save(rating);
        }
        System.out.println("rating data migration complete.");
    }
}
