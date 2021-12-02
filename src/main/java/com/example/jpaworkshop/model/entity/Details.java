package com.example.jpaworkshop.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

import static com.example.jpaworkshop.model.constants.EntityConstants.GENERATOR;
import static com.example.jpaworkshop.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private int detailsId;
    @Column(unique = true)
    private String email;
    private String name;
    private LocalDate birthDate;

    public Details(int detailsId, String email, String name, LocalDate birthDate) {
        this.detailsId = detailsId;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Details() {
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
