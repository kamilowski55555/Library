package com.springboot.crud.SpringBootCRUDApp.controller;


import com.springboot.crud.SpringBootCRUDApp.models.Book;
import com.springboot.crud.SpringBootCRUDApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping( "/viewBook")
    public String viewBooks(String message, Model model) {
        List<Book> bookList = bookService.getAllBook();

        model.addAttribute("bkList",bookList);
        model.addAttribute("message", message);

        return "ViewBook";

    }

    @GetMapping("/addBook")
    public String newBook(@ModelAttribute("message") String message, Model model) {
        Book bk = new Book();
        model.addAttribute("bk", bk);
        model.addAttribute("message", message);


        return "AddBook";

    }

    @PostMapping("/saveBook")
    public String saveBook(Book book, RedirectAttributes redirectAttributes) {
        if (bookService.saveOrUpdateBook(book)){
            redirectAttributes.addFlashAttribute("message", "Save Success");
            return "redirect:/viewBook";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addBook";
    }

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable Long id, String message, Model model) {

        Book book = bookService.getBookId(id);
        model.addAttribute("bk", book);
        model.addAttribute("message",message);

        return "EditBook";
    }

    @PostMapping("/editSaveBook")
    public String editSaveBook(Book bk, RedirectAttributes redirectAttributes) {

        if (bookService.saveOrUpdateBook(bk)) {
            redirectAttributes.addFlashAttribute("message","Edit Success");
            return "redirect:/viewBook";
        }
        redirectAttributes.addFlashAttribute("message","Edit Failure");
        return "redirect:/editBook/" + bk.getId();

    }
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        if (bookService.deleteBook(id)){
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            return "redirect:/viewBook";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewBook";

    }

}
