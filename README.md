# Movie Catalog Application
This is a simple RESTful API built using Spring Boot for a movie catalog application. The API allows users to perform basic CRUD (Create, Read, Update, Delete) operations on movie data.

## Dependencies
The following dependencies are used in this project:
- Spring Boot
- Spring Data JPA
- Lombok

## Installation
To run this project on your local machine, follow these steps:

Clone the repository:
```bash
git clone https://github.com/HalfBind/Milestone-1-Application.git
```
Navigate to the project directory:
```bash
cd Milestone-1-Application
```
Build the project:
```bash
mvn clean install
```
Run the project:
```base
mvn spring-boot:run
```

## Usage
### Endpoints
The API has the following endpoints:

- GET /ratings/{score}: Returns the movies that has rating average is greater than or equeal to {score}.
- POST /movies: Adds a new movie to the db.
- POST /movies/{id}: add the movie with the specified id.
- PUT /movies/{id}: Replaces the movie with the specified id.
- GET /movies: Returns a list of all movies in the catalog. 
- GET /movies/{id}: Returns the movie with the specified id.
- POST /ratings: Adds a new rating to the db.
- POST /ratings/{id}: add the rating with the specified id.
- PUT /ratings/{id}: Replaces the movie with the specified id.
- GET /users/{id}: Returns the user with the specified id.
- POST /users: Adds a new user to the db.
- POST /users/{id}: add the user with the specified id.
- PUT /users/{id}: Replaces the user with the specified id.

Example Requests
To get a list of movies that has average rating score greater than or equal to input:

```bash
GET http://localhost:8080/ratings/{score}
```

To get a list of all movies:

```bash
GET http://localhost:8080/movies
```

To get a specific movie:

```bash
GET http://localhost:8080/movies/1
```

To add a new movie:

```bash
POST http://localhost:8080/movies

Content-Type: application/json

{
  "title": "The Shawshank Redemption",
  "genre": "Drama"
}
```

To update a movie:

```bash
POST http://localhost:8080/movies/1
Content-Type: application/json

{
    "title": "The Shawshank Redemption",
    "genre": "Drama"
}
```

To replace a movie:

```bash
PUT http://localhost:8080/movies/1
Content-Type: application/json

{
    "title": "The Shawshank Redemption",
    "genre": "Drama"
}
```
### Credits
This project was created as part of the CSE 364 course at UNIST.
