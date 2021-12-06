package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.BookDAO;
import com.example.jpaworkshop.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public class BookDAORepository implements BookDAO {

    private final EntityManager entityManager;

    @Autowired
    public BookDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Book create(Book entity) {
        if(entity == null) throw new IllegalArgumentException("Entity is null");
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public Book update(Book entity) {
        if(entity == null) throw new IllegalArgumentException("Entity is null");
        entityManager.merge(entity);

        return entity;
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}
