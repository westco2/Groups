package com.project.groups.controller;

import com.project.groups.command.GroupVO;
import com.project.groups.group.GroupService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;




    @PostMapping("/groupdetail")
    public String groupdetail(@RequestParam("group_no") Integer group_no, Model model){
        Criteria cri = new Criteria(1,5);
        PageVO vo = new PageVO(cri, groupService.getstdtotal(cri,group_no));
        model.addAttribute("group", groupService.getgroupdetail(group_no));
        model.addAttribute("std", groupService.getgroupstdinfo(cri, group_no));
        model.addAttribute("pageVO",vo);
        return "group/groupdetail";
    }

    @GetMapping("/groupList")
    public String groupList(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();
        model.addAttribute("list",groupService.getgrouplist(sessionId));
        return "group/groupList";
    }

    @GetMapping("/groupreg")
    public String groupreg(){

        return "group/groupreg";
    }

    @PostMapping("/groupregForm")
    public String groupregForm(GroupVO vo, RedirectAttributes ra){
        System.out.println("실행");
        System.out.println(vo);

        if(groupService.groupregForm(vo)==1) ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        else ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 1566-6666");

        return "redirect:/group/groupList";
    }

    @GetMapping("/userGroupList")
    public String userGroupList(Model model){
        model.addAttribute("glist", groupService.getallgrouplist());
        return "group/userGroupList";
    }

    @PostMapping("/groupstupdate")
    public String groupstupdate(@RequestParam("group_non") List<Integer> groupNos) {
        List<GroupVO> list = new ArrayList<>();

        for(Integer i : groupNos){
            list.add(GroupVO.builder().group_non(i).build());

        }

        groupService.groupstupdate(list);

        return "redirect:/group/groupList";
    }

    @PostMapping("/groupstbupdate")
    public String groupstbupdate(@RequestParam("group_non") List<Integer> groupNos){
        List<GroupVO> list = new ArrayList<>();

        for(Integer i : groupNos){
            list.add(GroupVO.builder().group_non(i).build());

        }

        groupService.groupstbupdate(list);
        return "redirect:/group/groupList";
    }
    @PostMapping("/groupdelete")
    public String groupdelete(@RequestParam("group_non") List<Integer> groupNos){
        List<GroupVO> list = new ArrayList<>();



        for(Integer i : groupNos){
            list.add(GroupVO.builder().group_non(i).build());

        }


        groupService.groupdelete(list);
        return "redirect:/group/groupList";
    }


    @GetMapping("groupjoiner")
    public String groupjoiner(GroupVO vo, RedirectAttributes ra){


        if(groupService.groupjoin(vo)==1) ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        else ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        return "redirect:/group/userGroupList";
    }

    //그룹 승인하기
    @PostMapping("/groupjoinap")
    public String groupjoinap(@RequestParam("group_no") Integer group_no, @RequestParam("login_id") List<String> ids){
        List<GroupVO> vo = new ArrayList<>();
        ids.forEach(a->
                vo.add(GroupVO.builder().group_no(group_no).login_id(a).build())
                );
        groupService.groupjoinap(vo);
        return "redirect:/group/groupList";
    }




}
