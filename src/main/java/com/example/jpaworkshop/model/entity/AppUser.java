package com.example.jpaworkshop.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.jpaworkshop.model.constants.EntityConstants.GENERATOR;
import static com.example.jpaworkshop.model.constants.EntityConstants.UUID_GENERATOR;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy = "borrower"
    )
    private List<BookLoan> loans = new ArrayList<>();

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

    public List<BookLoan> getLoans() {
        return loans;
    }

    public void setLoans(List<BookLoan> loans) {
        if(loans == null) loans = new ArrayList<>();

        if(loans.isEmpty()){
            if(this.loans != null){
                this.loans.forEach(bookLoan -> bookLoan.setBorrower(null));
            }
        }else{
            loans.forEach(bookLoan -> bookLoan.setBorrower(this));
        }
        this.loans = loans;
    }
    public void addLoan(BookLoan bookLoan){
        if(bookLoan == null) throw new IllegalArgumentException("BookLoan was null");
        if(loans == null) loans = new ArrayList<>();
        if(!loans.contains(bookLoan)){
            if(bookLoan.getBook().isAvailable()){
                loans.add(bookLoan);
                bookLoan.setBorrower(this);
                bookLoan.getBook().setAvailable(false);
            }
        }

    }

    public void removeLoan(BookLoan bookLoan){
        if(bookLoan == null) throw new IllegalArgumentException("Book loan was null");
        if(loans == null) loans = new ArrayList<>();
        if(this.loans.contains(bookLoan)){
            loans.remove(bookLoan);
            bookLoan.setBorrower(this);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return AppUserId == appUser.AppUserId && Objects.equals(username, appUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AppUserId, username);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "AppUserId=" + AppUserId +
                ", username='" + username + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
