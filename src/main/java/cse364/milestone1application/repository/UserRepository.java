package cse364.milestone1application.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cse364.milestone1application.domain.Movie;
import cse364.milestone1application.domain.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User findById(Long id) {
        return em.createQuery("select u from User u where u.id = :id", User.class)
            .setParameter("id", id)
            .getSingleResult();
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
            .getResultList();
    }

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public void replace(User newUser) {
        System.out.println(newUser.getId());
        User oldUser = em.createQuery("select u from User u where u.id = :id", User.class)
            .setParameter("id", newUser.getId())
            .getSingleResult();

        System.out.println(oldUser);

        em.remove(oldUser);
        em.persist(newUser);
    }
}
