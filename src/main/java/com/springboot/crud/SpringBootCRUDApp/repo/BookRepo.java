package com.springboot.crud.SpringBootCRUDApp.repo;

import com.springboot.crud.SpringBootCRUDApp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository <Book,Long> {

}
