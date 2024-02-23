package org.example.service;


import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    T save(T entity);

    T findById(Integer id) throws SQLException;

    List<T> findAll();

    boolean deleteById(Integer id);
}