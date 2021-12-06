package com.example.jpaworkshop.data.interfaces;

import com.example.jpaworkshop.model.entity.BookLoan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookLoanDAO extends GenericCRUD<BookLoan, Integer> {
}
