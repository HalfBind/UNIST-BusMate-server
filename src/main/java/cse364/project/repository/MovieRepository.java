package cse364.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cse364.project.domain.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}
