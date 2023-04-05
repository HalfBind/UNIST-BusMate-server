package cse364.milestone1application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cse364.milestone1application.domain.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
}
