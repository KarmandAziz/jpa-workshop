package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.AppUserDAO;
import com.example.jpaworkshop.model.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AppUserDAORepository implements AppUserDAO {

    private final EntityManager entityManager;

    @Autowired
    public AppUserDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public AppUser create(AppUser entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public AppUser update(AppUser entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        return entityManager.merge(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AppUser> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("select a from AppUser a", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}
