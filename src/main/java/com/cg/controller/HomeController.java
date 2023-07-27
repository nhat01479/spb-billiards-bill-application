package com.cg.controller;

import com.cg.model.User;
import com.cg.service.user.IUserService;
import com.cg.ultis.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private AppUtils appUtils;
    @Autowired
    private IUserService userService;

    //    @GetMapping
//    public String showHomePage(){
//        return "index";
//    }
//
//    @GetMapping("/products")
//    public String showDashboard() {
//        return "/product/dashboard";
//    }
//
//
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }
//    @GetMapping("/register")
//    public String showRegisterPage() {
//        return "register";
//    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "home";
    }
    @GetMapping("/products")
    public String showListProduct(Model model) {
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "/product/dashboard";
    }
    @GetMapping("/desks")
    public String showListDesk(Model model) {
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "/desk/listDesk";
    }
    @GetMapping("/users")
    public String showListUser(Model model) {
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "/user/listUser";
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

