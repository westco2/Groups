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

    @GetMapping("/main2")
    public String main2(){
        return "homepage/main2";
    }


    
    @GetMapping("/payment")
    public String payment(){
        return "homepage/subscription/payment"; 
    }
    
    @GetMapping("/account")
    public String account(){
        return "homepage/subscription/account"; 
    }
    
    @GetMapping("/kakaopay")
    public String kakaopay(){
        return "homepage/subscription/kakaopay"; 
    }
    
    @GetMapping("/card")
    public String card(){
        return "homepage/subscription/card"; 
    }
    
    @GetMapping("/fna")
    public String fna(){
        return "homepage/fna"; 
    }
}
