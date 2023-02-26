package pl.bdygasinski.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import pl.bdygasinski.annotation.MovieRepo;
import pl.bdygasinski.exception.repository.EntityNotFoundException;
import pl.bdygasinski.exception.repository.MovieNotFoundRepositoryException;
import pl.bdygasinski.model.Movie;

import java.util.List;

@ApplicationScoped
@MovieRepo
public class MovieRepository implements Repository<Movie> {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void add(Movie entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Movie entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public Movie update(Movie entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Movie findById(Long id) throws MovieNotFoundRepositoryException {
        try {
            return entityManager.createNamedQuery(Movie.FIND_BY_ID, Movie.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (PersistenceException e) {
            throw new MovieNotFoundRepositoryException("Movie not found", e.getCause());
        }
    }

    @Override
    public List<Movie> getAll() {
        return entityManager.createNamedQuery(Movie.GET_ALL, Movie.class)
                .getResultList();
    }
}
