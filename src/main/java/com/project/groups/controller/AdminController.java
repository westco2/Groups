package com.project.groups.controller;

import com.project.groups.membersZ.service.MembersZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("MembersZService")
    private MembersZService membersZService;

    @RequestMapping("/userlist")
    public String userlist(Model model){
        model.addAttribute("list", membersZService.getuserlist());

        return "posts/list";
    }



}
