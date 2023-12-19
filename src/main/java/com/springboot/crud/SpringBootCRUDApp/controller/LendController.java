package com.springboot.crud.SpringBootCRUDApp.controller;

import com.springboot.crud.SpringBootCRUDApp.models.Lend;
import com.springboot.crud.SpringBootCRUDApp.service.LendService;
import com.springboot.crud.SpringBootCRUDApp.service.BookService;
import com.springboot.crud.SpringBootCRUDApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class LendController {

    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    // Wyświetlanie listy wypożyczeń
    @GetMapping("/viewLends")
    public String viewLends(Model model) {
        model.addAttribute("lends", lendService.getAllLends());
        return "ViewLends"; // Nazwa szablonu Thymeleaf do wyświetlania wypożyczeń
    }

    // Formularz do dodawania nowego wypożyczenia
    @GetMapping("/addLend")
    public String addLendForm(Model model) {
        model.addAttribute("users", userService.getActiveUsers()); // Aktywni użytkownicy
        model.addAttribute("books", bookService.getAvailableBooks()); // Dostępne książki
        model.addAttribute("lend", new Lend());
        return "AddLend"; // Nazwa szablonu Thymeleaf do dodawania wypożyczenia
    }

    // Zapis nowego wypożyczenia
    @PostMapping("/saveLend")
    public String saveLend(@ModelAttribute("lend") Lend lend, RedirectAttributes redirectAttributes) {
        if (lendService.addLend(lend.getUser().getUserId(), lend.getBook().getId(), lend.getStartDate(), lend.getEndDate()) != null) {
            redirectAttributes.addFlashAttribute("message", "Lend added successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to add lend");
        }
        return "redirect:/viewLends";
    }

    // Usuwanie wypożyczenia
    @GetMapping("/deleteLend/{lendId}")
    public String deleteLend(@PathVariable Long lendId, RedirectAttributes redirectAttributes) {
        if (lendService.deleteLend(lendId)) {
            redirectAttributes.addFlashAttribute("message", "Lend deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to delete lend");
        }
        return "redirect:/viewLends";
    }
}