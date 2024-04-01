package com.project.groups.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("/main")
    public String main(){
        return "homepage/main";
    }

}
