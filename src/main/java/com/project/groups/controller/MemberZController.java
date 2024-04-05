package com.project.groups.controller;

import com.project.groups.command.MemberVO;

import com.project.groups.membersZ.service.MembersZService;
import com.project.groups.membersZ.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public MemberZController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/joinFormHJ") //회원가입하는 페이지
    public String joinForm(){
        return "memberZ/joinFormHJ";
    }
    @PostMapping("/memberreg") //회원가입에서 입력된 값을 받음
    public String memberreg(MemberVO memberVO){
        String pswd = passwordEncoder.encode(memberVO.getPswd());
        System.out.println("pswd = " + pswd);
        memberVO.setPswd(pswd);
        boolean checking = membersZService.memberreg(memberVO); //boolean 으로 회원가입 성공 확인
        System.out.println("checking = " + checking);
        return "redirect:/login";
    }

    @ResponseBody
    @PostMapping("/memberidcheck")  //회원가입 id 중복 체크
    public int memberidcheck(@RequestParam("id") String memberidcheck){
        int checkingid = membersZService.memberidcheck(memberidcheck);
        return checkingid;
                          }

    @ResponseBody
    @PostMapping("/membernickcheck") //회원가입 닉네임 중복 체크
    public int membernickcheck(@RequestParam("nick") String membernickcheck){
        int checkingnick = membersZService.membernickcheck(membernickcheck);
        return checkingnick;
    }
    //컴파일러 컨트롤러
    @GetMapping("/javacompilerZ/CompilerTestZ")
    public String CompilerTestZ(){
        return ("/javacompilerZ/CompilerTestZ");
    }

    //로그인 컨트롤
    @PostMapping("/login")
    public void loginchecking(
        @RequestParam("username") String login_id,
        @RequestParam("password") String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(login_id);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);

        Authentication authentication = authenticationManager.authenticate(token);

        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
        }

    }
}
