package cse364.milestone1application.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.domain.User;
import cse364.milestone1application.repository.UserRepository;
import cse364.milestone1application.util.Migrator;

@RestController
@RequestMapping(value = "users/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) throws IOException {
        this.userRepository = userRepository;
        List<User> users = Migrator.getUsers();
        System.out.println("user data migration progressing...");

        for (User user : users) {
            this.userRepository.save(user);
            if (user.getId() > User.idCounter) {
                User.idCounter = user.getId();
            }
        }
        System.out.println("user data migration complete.");
    }

    @GetMapping()
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping()
    public User post(@RequestBody UserDto userDto) {
        User user = new User();
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setOccupation(userDto.getOccupation());
        user.setZipCode(userDto.getZipCode());
        return userRepository.save(user);
    }

    @PostMapping("/{id}")
    public User postById(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = new User();
        user.setId(id);
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setOccupation(userDto.getOccupation());
        user.setZipCode(userDto.getZipCode());
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User replace(@PathVariable Long id, @RequestBody UserDto userDto) {
        userRepository.deleteById(id);
        User user = new User();
        user.setId(id);
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setOccupation(userDto.getOccupation());
        user.setZipCode(userDto.getZipCode());
        return userRepository.save(user);
    }
}
