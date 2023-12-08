package com.springboot.crud.SpringBootCRUDApp.repo;

import com.springboot.crud.SpringBootCRUDApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByIsActive(boolean isActive);
}