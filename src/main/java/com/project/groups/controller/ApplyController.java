package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.membersZ.service.ApplyService;
import com.project.groups.membersZ.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/memberZ")
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @GetMapping("/applymember")
    public String applymember(Model model){
        List<MemberVO> applymemberlist = applyService.applymemberlist();
        System.out.println("applymemberlist = " + applymemberlist);
        model.addAttribute("amlist", applymemberlist);
        System.out.println("model = " + model);
        return "memberZ/applymember";
    }

    @PostMapping("/approvemember")
    public String approvemember(@RequestParam("loginId") String loginId){
        System.out.println("loginId = " + loginId);
        applyService.approveteacher(loginId);
        return "redirect:/memberZ/applymember";
    }

    @GetMapping("/tierchoice")
    public String tierchoice(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
        }

        return "memberZ/tierchoice";
    }
}
