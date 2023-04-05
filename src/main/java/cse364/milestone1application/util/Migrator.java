package cse364.milestone1application.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.domain.Rating;
import cse364.milestone1application.domain.User;

public class Migrator {

    public static List<Movie> getMovies() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/movies.dat"));
        List<Movie> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into fields
            String[] fields = line.split("::");

            Long id = Long.parseLong(fields[0]);
            String title = fields[1];
            String genre = fields[2];

            Movie movie = new Movie(id, title, genre);
            result.add(movie);
        }
        return result;
    }

    public static List<User> getUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/users.dat"));
        List<User> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into fields
            String[] fields = line.split("::");

            Long id = Long.parseLong(fields[0]);
            String gender = fields[1];
            Long age = Long.parseLong(fields[2]);
            Long occupation = Long.parseLong(fields[3]);
            String zipCode = fields[4];

            User user = new User(id, gender, age, occupation, zipCode);
            result.add(user);
        }
        return result;
    }

    public static List<Rating> getRatings() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/ratings.dat"));
        List<Rating> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split("::");

            Long userId = Long.parseLong(fields[0]);
            Long movieId = Long.parseLong(fields[1]);
            Double ratingScore = Double.parseDouble(fields[2]);
            String timestamp = fields[3];

            Rating rating = new Rating(userId, movieId, ratingScore, timestamp);
            result.add(rating);
        }
        return result;
    }

}