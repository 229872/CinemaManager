package pl.bdygasinski.repository;

import jakarta.persistence.PersistenceException;
import pl.bdygasinski.exception.repository.EntityNotFoundException;
import pl.bdygasinski.model.AbstractEntity;

import java.util.List;

public interface Repository <T extends AbstractEntity> {
    void add(T entity);
    void delete(T entity);
    T update(T entity);
    T findById(Long id) throws EntityNotFoundException;
    List<T> getAll();
}
