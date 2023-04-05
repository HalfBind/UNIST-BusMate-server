package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.User;
import cse364.milestone1application.repository.UserRepository;
import cse364.milestone1application.util.Migrator;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) throws IOException {
        this.userRepository = userRepository;
        List<User> users = Migrator.getUsers();
        System.out.println("user data migration progressing...");

        for (User user : users) {
            this.userRepository.save(user);
        }
        System.out.println("user data migration complete.");
    }
}
