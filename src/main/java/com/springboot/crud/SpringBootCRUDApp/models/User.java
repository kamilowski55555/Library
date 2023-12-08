package com.springboot.crud.SpringBootCRUDApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Nazwa tabeli w bazie danych
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private boolean isActive;
    private String userName;
    private String userEmail;

    public User() {
        // Ustawienie domyślnej wartości dla isActive, np. nowy użytkownik jest domyślnie aktywny
        this.isActive = true;
    }

    public User(long userId, String userName, String userEmail, boolean isActive) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.isActive = isActive;
    }

    // Gettery i settery
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Metoda toString, jeśli chcesz łatwo wyświetlać informacje o użytkowniku
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", isActive=" + isActive +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}