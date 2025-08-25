package ru.kke.springbootcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public @ResponseBody String getHelloWorld(@RequestParam(defaultValue = "world") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
