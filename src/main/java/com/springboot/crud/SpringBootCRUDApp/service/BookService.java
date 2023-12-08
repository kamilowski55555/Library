package com.springboot.crud.SpringBootCRUDApp.service;

import com.springboot.crud.SpringBootCRUDApp.models.Book;
import com.springboot.crud.SpringBootCRUDApp.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public List<Book> getAllBook(){
        List<Book> list = new ArrayList<>();
        bookRepo.findAll().forEach(book -> list.add(book));
        return list;
    }

    public Book getBookId(Long id) {
        return bookRepo.findById(id).get();
    }

    public boolean saveOrUpdateBook (Book book) {
        Book bk = bookRepo.save(book);

        if (bk != null && bookRepo.findById(bk.getId()) != null) {
            return true;
        }
        return false;
    }
    public void setBookAvailability(long bookId, boolean isAvailable) {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book != null) {
            book.setAvailable(isAvailable);
            bookRepo.save(book);
        }
    }
    public List<Book> getAvailableBooks() {
        return bookRepo.findByIsAvailable(true);
    }


    public boolean deleteBook(Long id) {
        bookRepo.deleteById(id);
        if (bookRepo.findById(id) != null) {
            return true;
        }
        return false;
    }


}
