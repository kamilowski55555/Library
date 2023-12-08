package com.springboot.crud.SpringBootCRUDApp.service;

import com.springboot.crud.SpringBootCRUDApp.models.User;
import com.springboot.crud.SpringBootCRUDApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.orElse(null);
    }

    public User saveOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    // Metoda do negowania statusu aktywności użytkownika
    public boolean negateActive(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setActive(!updatedUser.isActive()); // Neguje wartość isActive
            userRepo.save(updatedUser);
            return true;
        }
        return false;
    }

    public List<User> getActiveUsers() {
        return userRepo.findByIsActive(true);
    }
}