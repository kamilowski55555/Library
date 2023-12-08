package com.springboot.crud.SpringBootCRUDApp.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lends")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lendId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private Book book;

    private LocalDate startDate;
    private LocalDate endDate;

    public Lend() {
    }

    public Lend(User user, Book book, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Gettery i settery
    public long getLendId() {
        return lendId;
    }

    public void setLendId(long lendId) {
        this.lendId = lendId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Metoda toString() jeśli potrzebujesz
    @Override
    public String toString() {
        return "Lend{" +
                "lendId=" + lendId +
                ", user=" + user +
                ", book=" + book +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
