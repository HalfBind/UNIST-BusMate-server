package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.domain.Rating;
import cse364.milestone1application.repository.MovieRepository;
import cse364.milestone1application.repository.RatingRepository;
import cse364.milestone1application.util.Migrator;

@RestController
@RequestMapping(value = "ratings/")
public class RatingController {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public RatingController(RatingRepository ratingRepository,
        MovieRepository movieRepository) throws IOException {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        List<Rating> ratings = Migrator.getRatings();

        System.out.println("rating data migration progressing...");
        for (Rating rating : ratings) {
            this.ratingRepository.save(rating);
        }
        System.out.println("rating data migration complete.");
    }

    @GetMapping()
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @GetMapping("/{standardRating}")
    public List<MovieDto> getByRatingScore(@PathVariable Long standardRating) throws IOException {
        List<Movie> movies = movieRepository.findAll();
        List<Rating> ratings = ratingRepository.findAll();
        List<MovieDto> result = new ArrayList<>();
        for (Movie movie : movies) {
            Long movieId = movie.getId();
            Double averageRating = ratings
                .stream()
                .filter(rating -> rating.getMovieId().equals(movieId))
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(Double.NaN);

            if (averageRating >= standardRating) {
                MovieDto movieDto = new MovieDto(movie.getTitle(), movie.getGenre());
                result.add(movieDto);
            }
        }
        return result;
    }

    @PostMapping()
    public Rating post(@RequestBody RatingDto ratingDto) {
        Rating rating = new Rating();
        rating.setMovieId(ratingDto.getMovieId());
        rating.setUserId(ratingDto.getUserId());
        rating.setRating(ratingDto.getRating());
        rating.setTimestamp(ratingDto.getTimestamp());
        return ratingRepository.save(rating);
    }

    @PostMapping("/{id}")
    public Rating postById(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        Rating rating = new Rating();
        // rating.setId(id);
        rating.setMovieId(ratingDto.getMovieId());
        rating.setUserId(ratingDto.getUserId());
        rating.setRating(ratingDto.getRating());
        rating.setTimestamp(ratingDto.getTimestamp());
        return ratingRepository.save(rating);
    }

    @PutMapping("/{id}")
    public Rating replace(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        ratingRepository.deleteById(id);
        Rating rating = new Rating();
        // rating.setId(id);
        rating.setMovieId(ratingDto.getMovieId());
        rating.setUserId(ratingDto.getUserId());
        rating.setRating(ratingDto.getRating());
        rating.setTimestamp(ratingDto.getTimestamp());
        return ratingRepository.save(rating);
    }
}
