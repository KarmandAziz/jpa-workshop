package com.example.jpaworkshop.data.dao;

import com.example.jpaworkshop.data.interfaces.BookLoanDAO;
import com.example.jpaworkshop.model.entity.BookLoan;

import java.util.Collection;
import java.util.Optional;

public class BookLoanDAORepository implements BookLoanDAO {
    @Override
    public BookLoan create(BookLoan entity) {
        return null;
    }

    @Override
    public BookLoan update(BookLoan entity) {
        return null;
    }

    @Override
    public Optional<BookLoan> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Collection<BookLoan> findAll() {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
