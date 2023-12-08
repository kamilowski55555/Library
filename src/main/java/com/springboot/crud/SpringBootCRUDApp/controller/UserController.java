package com.springboot.crud.SpringBootCRUDApp.controller;

import com.springboot.crud.SpringBootCRUDApp.models.User;
import com.springboot.crud.SpringBootCRUDApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // Pokaż listę użytkowników
    @GetMapping("/viewUsers")
    public String viewUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "ViewUsers"; // nazwa szablonu Thymeleaf do wyświetlenia użytkowników
    }

    // Dodanie nowego użytkownika (formularz)
    @GetMapping("/addUser")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "AddUser"; // nazwa szablonu Thymeleaf do dodania użytkownika
    }

    // Zapis nowego użytkownika
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userService.saveOrUpdateUser(user);
        redirectAttributes.addFlashAttribute("message", "User saved successfully");
        return "redirect:/viewUsers";
    }

    // Zmiana statusu aktywności użytkownika
    @GetMapping("/changeUserStatus/{userId}")
    public String changeUserStatus(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        boolean isChanged = userService.negateActive(userId);
        if(isChanged) {
            redirectAttributes.addFlashAttribute("message", "User status changed successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to change user status");
        }
        return "redirect:/viewUsers";
    }

    @GetMapping("/editUser/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "EditUser";
        } else {
            // Możesz dodać komunikat o błędzie, jeśli użytkownik nie istnieje.
            return "redirect:/viewUsers";
        }
    }

    // Zapisz zmiany po edycji użytkownika
    @PostMapping("/editSaveUser")
    public String editSaveUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        User updatedUser = userService.saveOrUpdateUser(user);
        if (updatedUser != null) {
            redirectAttributes.addFlashAttribute("message", "User updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to update user");
        }
        return "redirect:/viewUsers";
    }

}