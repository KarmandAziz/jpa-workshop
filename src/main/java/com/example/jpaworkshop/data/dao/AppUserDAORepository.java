package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.AppUserDAO;
import com.example.jpaworkshop.model.entity.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
public class AppUserDAORepository implements AppUserDAO {

    private final EntityManager entityManager;

    public AppUserDAORepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public AppUser create(AppUser entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public AppUser update(AppUser entity) {
        if(entity == null) throw new IllegalArgumentException("Entity was null");
        return entityManager.merge(entity);
    }

    @Override
    public Optional<AppUser> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }

    @Override
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("select a from AppUser a", AppUser.class)
                .getResultList();
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(entityManager::remove);

    }
}
