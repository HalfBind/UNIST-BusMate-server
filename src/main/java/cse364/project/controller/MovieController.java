package cse364.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cse364.project.domain.Movie;
import cse364.project.repository.MovieRepository;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/ratings/{standardRating}")
    public List<Movie> getAllGreaterThanOrEqual(@PathVariable Integer standardRating) {
        return movieRepository
            .findAll()
            .stream()
            .filter(movie -> movie.getRating() >= standardRating) //TODO implement getRating()
            .collect(Collectors.toList());
    }
}
