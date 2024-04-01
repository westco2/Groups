package com.project.groups.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class GroupController {
    @GetMapping("/groupList")
    public String groupList(){

        return "group/groupList";
    }

    @GetMapping("/groupreg")
    public String groupreg(){

        return "group/groupreg";
    }

    @GetMapping("/groupdetail")
    public String groupdetail(){

        return "group/groupdetail";
    }


}
