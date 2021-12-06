package com.example.jpaworkshop.data.interfaces;

import com.example.jpaworkshop.model.entity.AppUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface AppUserDAO extends GenericCRUD<AppUser, Integer>{
}
