package cse364.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cse364.project.domain.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
}
