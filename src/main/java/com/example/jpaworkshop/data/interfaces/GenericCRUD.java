package com.example.jpaworkshop.data.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface GenericCRUD <T, ID>{
    T create(T entity);
    T update(T entity);
    Optional<T> findById(ID id);
    Collection<T> findAll();
    void delete(ID id);
}
