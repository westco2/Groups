package com.project.groups.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noti")
public class NotiController {
    @GetMapping("/notilist")
    public String notilist(){

        return "noti/notilist";
    }

    @GetMapping("/notireg")
    public String notireg(){

        return "noti/notireg";
    }

    @GetMapping("/notidetail")
    public String notidetail(){

        return "noti/notidetail";
    }
    @GetMapping("/notiupdate")
    public String notiupdate(){
    	
    	return "noti/notiupdate";
    }
    @GetMapping("/noticheck")
    public String notidelete(){
    	
    	return "noti/noticheck";
    }


}
