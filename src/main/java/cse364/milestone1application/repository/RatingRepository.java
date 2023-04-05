package cse364.milestone1application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cse364.milestone1application.domain.Rating;
import lombok.RequiredArgsConstructor;

@Repository
public interface RatingRepository extends MongoRepository<Rating, Long> {

}
