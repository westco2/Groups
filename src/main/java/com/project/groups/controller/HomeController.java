package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.membersZ.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication = " + authentication);
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getDetails());
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
        }
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
