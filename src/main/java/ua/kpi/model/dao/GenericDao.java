package ua.kpi.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {
    void create(E entity);
    Optional<E> find(Integer id);
    List<E> findAll();
    void update(E entity);
    void delete(Integer id);
}

