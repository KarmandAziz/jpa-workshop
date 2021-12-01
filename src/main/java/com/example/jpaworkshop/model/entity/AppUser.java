package com.example.jpaworkshop.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

import static com.example.jpaworkshop.model.constants.EntityConstants.GENERATOR;
import static com.example.jpaworkshop.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private int AppUserId;
    @Column(length = 30, unique = true)
    private String username;
    private String password;
    private LocalDate regDate;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fk_details_id", table = "app_user")
    private Details userDetails;

    public AppUser() {
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public AppUser(int appUserId, String username, String password, LocalDate regDate) {
        AppUserId = appUserId;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
    }


    public int getAppUserId() {
        return AppUserId;
    }

    public void setAppUserId(int appUserId) {
        AppUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
