package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.BookLoanDAO;
import com.example.jpaworkshop.model.entity.BookLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;
@Repository
@Transactional
public class BookLoanDAORepository implements BookLoanDAO {

    private final EntityManager entityManager;
    @Autowired
    public BookLoanDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public BookLoan create(BookLoan entity) {
        if(entity == null) throw new IllegalArgumentException("Entity is null");
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public BookLoan update(BookLoan entity) {
        if(entity == null) throw new IllegalArgumentException("Entity is null");
        entityManager.merge(entity);

        return entity;
    }

    @Override
    public Optional<BookLoan> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(BookLoan.class, id));
    }

    @Override
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT b FROM BookLoan b",BookLoan.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}
