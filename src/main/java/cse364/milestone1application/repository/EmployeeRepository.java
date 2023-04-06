package cse364.milestone1application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cse364.milestone1application.domain.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Long> {
}
