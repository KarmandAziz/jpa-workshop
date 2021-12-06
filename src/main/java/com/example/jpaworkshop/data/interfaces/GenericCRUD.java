package com.example.jpaworkshop.data.interfaces;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
public interface GenericCRUD <T, ID>{

    
    T create(T entity);
    T update(T entity);
    Optional<T> findById(ID id);
    Collection<T> findAll();
    void delete(ID id);
}
