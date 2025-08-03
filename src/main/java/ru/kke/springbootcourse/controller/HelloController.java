package ru.kke.springbootcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String getHelloWorld(@RequestParam String name, Model model) {
        return "Hello World!";
    }

}
