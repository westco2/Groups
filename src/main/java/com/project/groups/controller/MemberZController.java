package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //로그인, 회원가입에 관한 컨트롤러들
public class MemberZController {
    @GetMapping("/loginForm")
    public String loginForm(){
        return "/memberZ/loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "/memberZ/joinForm";
    }
    @PostMapping("/sibal")
    public String join(MemberVO memberVO){
        System.out.println("memberVO = " + memberVO);
        System.out.println("tq");
        return "/memberZ/loginForm";
    }
}
