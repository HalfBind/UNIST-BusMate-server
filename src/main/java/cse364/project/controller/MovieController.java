package cse364.project.controller;

import org.springframework.web.bind.annotation.RestController;

import cse364.project.repository.MovieRepository;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
