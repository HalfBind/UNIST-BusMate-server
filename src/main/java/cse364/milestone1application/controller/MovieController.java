package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.repository.MovieRepository;
import cse364.milestone1application.util.Migrator;

@RestController
@RequestMapping(value = "movies/")
public class MovieController {
    @Autowired
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) throws IOException {
        this.movieRepository = movieRepository;
        List<Movie> movies = Migrator.getMovies();
        System.out.println("movie data migration progressing...");
        for (Movie movie : movies) {
            movieRepository.save(movie);
            if (movie.getId() > Movie.idCounter) {
                Movie.idCounter = movie.getId();
            }
        }
        System.out.println("movie data migration complete.");
    }

    @GetMapping()
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Movie> getById(@PathVariable Long id) {
        return movieRepository.findById(id);
    }

    @PostMapping()
    public Movie post(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        return movieRepository.save(movie);
    }

    @PostMapping("/{id}")
    public Movie postById(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        Movie movie = new Movie(id, movieDto.getTitle(), movieDto.getGenre());
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie replace(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        movieRepository.deleteById(id);
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        return movieRepository.save(movie);
    }

}
