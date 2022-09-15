package com.health.healer.repository;

import java.sql.Connection;
import java.util.List;

public interface ReadOnlyRepository<T, ID> {

    List<T> findAll(Class<T> tClass, Connection connection);

    T findById(Class<T> tClass, ID id, Connection connection);

    String getTableName(String className);
}
