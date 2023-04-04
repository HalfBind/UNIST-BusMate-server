package cse364.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cse364.project.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
