package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.BookDAO;
import com.example.jpaworkshop.model.entity.Book;

import java.util.Collection;
import java.util.Optional;

public class BookDAORepository implements BookDAO {
    @Override
    public Book create(Book entity) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public Optional<Book> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Collection<Book> findAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
