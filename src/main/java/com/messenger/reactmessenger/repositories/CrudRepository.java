package com.messenger.reactmessenger.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll();

    void delete(T entity);

    // boolean existsById(ID primaryKey);
}