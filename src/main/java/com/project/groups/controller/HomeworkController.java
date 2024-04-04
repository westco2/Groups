package com.project.groups.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @GetMapping("/homeworklist")
    public String hoemworklist(){
        return "homework/homeworklist";
    }

    @GetMapping("/homeworkreg")
    public String homeworkreg(){
        return "homework/homeworkreg";
    }



    /* 학생 */

    @GetMapping("/myhomework")
    public String myhomework(){
        return "homework/myhomework";
    }

    @GetMapping("/myhomeworkdetail")
    public String myhomeworkdetail(){
        return "homework/myhomeworkdetail";
    }
}
