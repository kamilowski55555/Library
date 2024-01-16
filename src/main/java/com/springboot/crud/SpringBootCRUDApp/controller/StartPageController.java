package com.springboot.crud.SpringBootCRUDApp.controller;

import com.springboot.crud.SpringBootCRUDApp.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StartPageController {
    @GetMapping( "/startPage")
    public String startPage() {

        return "StartPage";

    }
}
