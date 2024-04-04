package com.project.groups.controller;

import com.project.groups.command.MemberVO;

import com.project.groups.membersZ.service.MembersZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller //로그인, 회원가입에 관한 컨트롤러들
public class MemberZController {

    @Autowired
    @Qualifier("MembersZService")
    private MembersZService membersZService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/loginFormHJ")
    public String loginForm(){
        return "loginFormHJ";
    }
    @PostMapping("/login")
    public String login(MemberVO memberVO){
        System.out.println("memberVO = " + memberVO);

        return "redirect:/";
    }
    @GetMapping("/joinFormHJ") //회원가입하는 페이지
    public String joinForm(){
        return "/memberZ/joinFormHJ";
    }
    @PostMapping("/memberreg") //회원가입에서 입력된 값을 받음
    public String memberreg(MemberVO memberVO){
        String pswd = passwordEncoder.encode(memberVO.getPswd());
        System.out.println("pswd = " + pswd);
        memberVO.setPswd(pswd);
        boolean checking = membersZService.memberreg(memberVO); //boolean 으로 회원가입 성공 확인
        System.out.println("checking = " + checking);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/memberidcheck")
    public int memberidcheck(@RequestParam("id") String memberidcheck){
        System.out.println("memberidcheck = " + memberidcheck);
        int checkingid = membersZService.memberidcheck(memberidcheck);
        System.out.println("checkingid = " + checkingid);
        return checkingid;
    }

    @ResponseBody
    @PostMapping("/membernickcheck")
    public int membernickcheck(
            @RequestParam("nick") String membernickcheck){
        System.out.println("membernickcheck = " + membernickcheck);
        int checkingnick = membersZService.membernickcheck(membernickcheck);
        System.out.println("checkingnick = " + checkingnick);
        return checkingnick;
    }

    @GetMapping("/javacompilerZ/CompilerTestZ")
    public String CompilerTestZ(){
        return ("/javacompilerZ/CompilerTestZ");
    }
}
