package cse364.milestone1application.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.domain.Rating;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RatingRepository {

    private final EntityManager em;

    public List<Rating> findAll() {
        return em.createQuery("select r from Rating r", Rating.class)
            .getResultList();
    }

    @Transactional
    public Rating save(Rating rating) {
        em.persist(rating);
        return rating;
    }

    @Transactional
    public void replace(Rating newRating) {
        System.out.println(newRating.getId());
        Rating oldRating = em.createQuery("select r from Rating r where r.id = :id", Rating.class)
            .setParameter("id", newRating.getId())
            .getSingleResult();

        System.out.println(oldRating);

        em.remove(oldRating);
        em.persist(newRating);
    }

}
