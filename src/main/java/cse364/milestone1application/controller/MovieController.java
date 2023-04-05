package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.repository.MovieRepository;
import cse364.milestone1application.util.Migrator;

@RestController
@RequestMapping(value = "movie/")
public class MovieController {
    @Autowired
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) throws IOException {
        this.movieRepository = movieRepository;
        List<Movie> movies = Migrator.getMovies();
        System.out.println("movie data migration progressing...");
        for (Movie movie : movies) {
            movieRepository.save(movie);
        }
        System.out.println("movie data migration complete.");
    }


}
