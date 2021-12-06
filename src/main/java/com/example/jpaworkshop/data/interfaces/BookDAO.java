package com.example.jpaworkshop.data.interfaces;

import com.example.jpaworkshop.model.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookDAO extends GenericCRUD<Book,Integer> {
}
