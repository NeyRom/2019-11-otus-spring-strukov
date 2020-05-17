package ru.strukov.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Roman Strukov
 */

@Controller
public class BookController {

    @GetMapping("/")
    public String listBooks() {
        return "index";
    }
}
