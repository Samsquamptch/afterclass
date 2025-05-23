package io.samsquamptch.afterclass.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/group/{id}")
    public String group(@PathVariable int id) {
        return "group";
    }
}
