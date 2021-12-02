package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.AuthorDAO;
import com.example.jpaworkshop.model.entity.Author;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

public class AuthorDAORepository implements AuthorDAO {

    private final EntityManager entityManager;

    public AuthorDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author create(Author entity) {
        if(entity == null) throw new IllegalArgumentException("Entity is null");
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author entity) {
       if(entity == null) throw new IllegalArgumentException("Entity is null");
       entityManager.merge(entity);
        return entity;
    }

    @Override
    public Optional<Author> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public Collection<Author> findAll() {
        return entityManager.createQuery("select a from Author a",Author.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}