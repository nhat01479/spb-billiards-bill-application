package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/admin")
    public String showDashboard() {
        return "/admin/dashboard";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

}
