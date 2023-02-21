package pl.bdygasinski.repository;

import pl.bdygasinski.model.AbstractEntity;

import java.util.List;

public interface Repository <T extends AbstractEntity> {
    void add(T entity);
    void delete(T entity);
    T update(T entity);
    T findById(Long id);
    List<T> getAll();
}
