package cse364.milestone1application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @GetMapping("movie/{id}")
    public Movie findOne(@PathVariable Long id) {
        return movieRepository.findById(id);
    }

    @PostMapping("movie/{id}")
    public Movie postMovie(@PathVariable Long id, @RequestParam(name = "title") String title,
        @RequestParam(name = "genre") String genre) {
        Movie movie = new Movie(id, title, genre);
        movieRepository.save(movie);
        System.out.println(movie);
        return movie;
    }

    @PutMapping("movie/{id}")
    public void putMovie(@PathVariable Long id, @RequestParam String title, @RequestParam String genre) {
        Movie movie = new Movie(id, title, genre);
        movieRepository.replace(movie);
    }
}
