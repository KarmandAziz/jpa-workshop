package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.DetailsDAO;
import com.example.jpaworkshop.model.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public class DetailsDAORepository implements DetailsDAO {

    private final EntityManager entityManager;

    @Autowired
    public DetailsDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Details create(Details entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");

        entityManager.persist(entity);

        return entity;
    }

    @Override
    public Details update(Details entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        return entityManager.merge(entity);
    }

    @Override
    public Optional<Details> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Details.class, id));
    }

    @Override
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d",Details.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}
