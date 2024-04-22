package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.group.GroupService;
import com.project.groups.homework.HomeworkService;
import com.project.groups.membersZ.service.CustomUserDetails;
import com.project.groups.qnaW.service.QnaWService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;
    @Autowired
    @Qualifier("QnaWService")
    private QnaWService qnaWService;

    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;

    @RequestMapping("/stdmypage")
    public String stdmypage(Model model, Criteria cri){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            model.addAttribute("membervo", memberVO);
            model.addAttribute("group",groupService.getgroupdetail(groupService.getmygroupno(memberVO.getLogin_id())));
            model.addAttribute("sol",homeworkService.solutionp(memberVO.getLogin_id()));
            model.addAttribute("np", homeworkService.notsolutionp(memberVO.getLogin_id()));
            PageVO vo = new PageVO(cri, homeworkService.myrecordtotal(memberVO.getLogin_id(), cri));
            model.addAttribute("work", homeworkService.myrecord(memberVO.getLogin_id(), cri));
            model.addAttribute("pageVO", vo);
        }



        return "mypage/studentmy";
    }
    @RequestMapping("/tchmypage")
    public String tchmypage(Model model){
        Criteria cri = new Criteria(1, 5);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            model.addAttribute("membervo", memberVO);
            model.addAttribute("list",groupService.getgrouplist(memberVO.getLogin_id(), cri));
            System.out.println(qnaWService.getList(cri, memberVO.getLogin_id()));
            model.addAttribute("qnavo", qnaWService.getList(cri, memberVO.getLogin_id()));
        }




        return "mypage/teachermy";
    }

    @RequestMapping("/admmypage")
    public String admmypage(Model model){


        System.out.println(groupService.getadmin());
        model.addAttribute("list", groupService.getadmin());
        return "mypage/adminmy";
    }

}
