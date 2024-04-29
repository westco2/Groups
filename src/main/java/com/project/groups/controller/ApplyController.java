package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.command.PaymentListVO;
import com.project.groups.membersZ.service.ApplyService;
import com.project.groups.membersZ.service.CustomUserDetails;
import com.project.groups.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/memberZ")
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private PaymentService paymentService;
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



    @GetMapping("/tierchoiceZ")
    public String tierchoiceZ(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
        }
        return "memberZ/tierchoiceZ";
    }

    @GetMapping("/paymentList") //결제내역조회
    public String paymentList(Model model){
        List<PaymentListVO> paymentListVOList = paymentService.paymentlistcheck();
        System.out.println("paymentListVOList = " + paymentListVOList);
        model.addAttribute("paymentList", paymentListVOList);
        System.out.println("model = " + model);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("loginingId", memberVO.getLogin_id());
        }
        System.out.println("model222 = " + model);
        return "memberZ/paymentList";
    }
}
