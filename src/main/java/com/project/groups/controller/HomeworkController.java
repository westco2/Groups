package com.project.groups.controller;

import com.project.groups.command.HomeWorkVO;
import com.project.groups.homework.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;

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

    /* 선생 숙제 등록 - 현준 */
    @PostMapping("/homeworkregForm")
    public String homeworkregForm(HomeWorkVO homeworkvo, RedirectAttributes ra){
        System.out.println("homeworkvo = " + homeworkvo); //homeWorkVO 값 확인
        if(homeworkService.homeworkregForm(homeworkvo) == 1 ) ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        else ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 1566-6666");

        return "redirect:homework/homeworklist";
    }
}


