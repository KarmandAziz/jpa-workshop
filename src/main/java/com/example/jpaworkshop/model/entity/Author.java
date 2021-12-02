package com.example.jpaworkshop.model.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int authorId;
    @Column(length = 30)
    private String firstName;
    @Column(length = 30)
    private String lastName;
    @ManyToMany(
            cascade = {CascadeType.DETACH,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private Set<Book> writtenBooks;

    public Author() {
    }

    public Author(int authorId, String firstName, String lastName, Set<Book> writtenBooks) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }
}
