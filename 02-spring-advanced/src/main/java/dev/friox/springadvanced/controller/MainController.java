package dev.friox.springadvanced.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        return "signin";
    }

    @GetMapping("/post_write")
    public String postWrite(Model model) {
        return "post_write";
    }

}
