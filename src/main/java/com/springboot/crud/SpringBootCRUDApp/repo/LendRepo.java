package com.springboot.crud.SpringBootCRUDApp.repo;

import com.springboot.crud.SpringBootCRUDApp.models.Lend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendRepo extends JpaRepository<Lend, Long> {
}