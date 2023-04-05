package cse364.milestone1application.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cse364.milestone1application.domain.Movie;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MovieRepository {
    private final EntityManager em;

    public Movie findById(Long id) {
        return em.createQuery("select m from Movie m where m.id = :id", Movie.class)
            .setParameter("id", id)
            .getSingleResult();
    }

    public List<Movie> findAll() {
        return em.createQuery("select m from Movie m", Movie.class)
            .getResultList();
    }

    @Transactional
    public Movie save(Movie movie) {
        em.persist(movie);
        return movie;
    }

    @Transactional
    public void replace(Movie newMovie) {
        System.out.println(newMovie.getId());
        Movie oldMovie = em.createQuery("select m from Movie m where m.id = :id", Movie.class)
            .setParameter("id", newMovie.getId())
            .getSingleResult();

        System.out.println(oldMovie);

        em.remove(oldMovie);
        em.persist(newMovie);
    }

}
