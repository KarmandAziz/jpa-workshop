package com.example.jpaworkshop.data.interfaces;

import com.example.jpaworkshop.model.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorDAO extends GenericCRUD<Author, Integer>{
}
