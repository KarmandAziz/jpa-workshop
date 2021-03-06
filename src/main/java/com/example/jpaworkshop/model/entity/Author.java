package com.example.jpaworkshop.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "id")
    private int authorId;
    private String firstName;
    private String lastName;
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            mappedBy = "authors"
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writtenBooks=" + writtenBooks +
                '}';
    }
}
