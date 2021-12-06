package com.example.jpaworkshop.model.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "id")
    private int bookId;
    private String isbn;
    @Column(length = 100)
    private String title;
    private boolean available = true;
    private int maxLoanDays;
    @ManyToMany(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    private Set<Author> authors = new HashSet<>();

    public Book() {
    }



    public Book(int bookId, String isbn, String title, int maxLoanDays) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Set<Author> getAuthors() {
        if(authors == null) authors = new HashSet<>();
        return authors;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void addAuthors(Author author) {
        if(author == null) throw new IllegalArgumentException("Author was null");
        if(authors == null) authors = new HashSet<>();
        authors.add(author);
        author.getWrittenBooks().add(this);
        }


    public void setAuthors(Set<Author> authors) {
        if(authors == null) authors = new HashSet<>();
        if(authors.isEmpty()){
            if(this.authors != null){
                this.authors.forEach(author -> author.setWrittenBooks(null));
            }
        }else{
            authors.forEach(author -> author.getWrittenBooks().add(this));
        }
        this.authors = authors;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(isbn == null) throw new IllegalArgumentException("Isbn was null");
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null) throw new IllegalArgumentException("Title was null");
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && maxLoanDays == book.maxLoanDays && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn, title, maxLoanDays);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                ", authors=" + authors +
                '}';
    }
}
