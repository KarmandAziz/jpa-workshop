package com.example.jpaworkshop.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "id")
    private int authorId;
    private String firstName;
    private String lastName;
    @ManyToMany(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY
    )
    private Set<Book> writtenBooks = new HashSet<>();

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
        if(writtenBooks == null) writtenBooks = new HashSet<>();
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        if(writtenBooks == null) writtenBooks = new HashSet<>();
        if(writtenBooks.isEmpty()){
            if(this.writtenBooks != null){
                this.writtenBooks.forEach(book -> book.getAuthors().remove(this));
            }
        }else{
            writtenBooks.forEach(book -> book.getAuthors().add(this));
        }
        this.writtenBooks = writtenBooks;
    }

}
