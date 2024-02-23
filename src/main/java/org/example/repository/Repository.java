package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T, K>{
    T findById(K id) throws SQLException;

    boolean deleteById(K id);



    List<T> findAll();

    T save(T t);


}
