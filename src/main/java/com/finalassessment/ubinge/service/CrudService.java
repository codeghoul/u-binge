package com.finalassessment.ubinge.service;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T save(T newObject);

    T update(T object, ID id);

    void delete(T object);

    void deleteById(ID id);
}