package com.springboot.crud.SpringBootCRUDApp.models;


import jakarta.persistence.*;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean isAvailable;
    private String name;
    private String author;


    public Book(long id, String name, String author, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public Book() {
        this.isAvailable = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
