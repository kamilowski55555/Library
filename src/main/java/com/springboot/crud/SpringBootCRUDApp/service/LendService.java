package com.springboot.crud.SpringBootCRUDApp.service;

import com.springboot.crud.SpringBootCRUDApp.models.Book;
import com.springboot.crud.SpringBootCRUDApp.models.Lend;
import com.springboot.crud.SpringBootCRUDApp.models.User;
import com.springboot.crud.SpringBootCRUDApp.repo.BookRepo;
import com.springboot.crud.SpringBootCRUDApp.repo.LendRepo;
import com.springboot.crud.SpringBootCRUDApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LendService {

    @Autowired
    private LendRepo lendRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    public Lend addLend(Long userId, Long bookId, LocalDate startDate, LocalDate endDate) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Book> book = bookRepo.findById(bookId);

        if (user.isPresent() && user.get().isActive() && book.isPresent() && book.get().isAvailable()) {
            Lend lend = new Lend();
            lend.setUser(user.get());
            lend.setBook(book.get());
            lend.setStartDate(startDate);
            lend.setEndDate(endDate);

            book.get().setAvailable(false);
            bookRepo.save(book.get());

            return lendRepo.save(lend);
        } else {
            return null; // Jeśli użytkownik nieaktywny lub książka niedostępna
        }
    }

    public boolean deleteLend(Long lendId) {
        Optional<Lend> lend = lendRepo.findById(lendId);
        if (lend.isPresent()) {
            Book book = lend.get().getBook();
            book.setAvailable(true);
            bookRepo.save(book);

            lendRepo.delete(lend.get());
            return true;
        } else {
            return false;
        }
    }

    // Metoda do pobierania wszystkich wypożyczeń
    public List<Lend> getAllLends() {
        return lendRepo.findAll();
    }
}