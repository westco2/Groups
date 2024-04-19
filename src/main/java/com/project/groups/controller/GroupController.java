package com.project.groups.controller;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.group.GroupService;
import com.project.groups.membersZ.service.CustomUserDetails;
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
        PageVO vo2 = new PageVO(cri, groupService.getdatainfototal(group_no,cri));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
            model.addAttribute("list",groupService.getdashboardt(group_no, memberVO.getLogin_id()) );
        }

        model.addAttribute("data",groupService.getdatainfo(group_no,cri));
        model.addAttribute("group", groupService.getgroupdetail(group_no));
        model.addAttribute("std", groupService.getgroupstdinfo(cri, group_no));
        model.addAttribute("pageVO",vo);
        model.addAttribute("pageVO2",vo2);
        return "group/groupdetail";
    }

    @GetMapping("/groupList")
    public String groupList(Criteria cri,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();
        PageVO vo = new PageVO(cri,groupService.getgrouplisttotal(sessionId, cri));
        model.addAttribute("list",groupService.getgrouplist(sessionId, cri));
        model.addAttribute("pageVO",vo);
        return "group/groupList";
    }

    @GetMapping("/groupreg")
    public String groupreg(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println(memberVO);
            model.addAttribute("membervo", memberVO);
        }

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
    public String userGroupList(Model model, Criteria cri){
        String login_id = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println(memberVO);
            login_id =  memberVO.getLogin_id();
        }

        if(groupService.aprv_yn(login_id) != 0) return "redirect:/mypage/stdmypage";
        if(groupService.aprv_null(login_id) != 0) return "redirect:/group/wait";
        PageVO vo = new PageVO(cri,groupService.getalltotal(cri));
        model.addAttribute("glist", groupService.getallgrouplist(cri));
        model.addAttribute("pageVO",vo);
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


    @GetMapping("/wait")
    public String waiting(Model model){
        String login_id = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println(memberVO);
            login_id =  memberVO.getLogin_id();
        }
        System.out.println(groupService.aprv_yn(login_id));
        if(groupService.aprv_yn(login_id) != 0) return "redirect:/mypage/stdmypage";
        System.out.println(login_id);
        GroupVO vo = groupService.groupwait(login_id);
        model.addAttribute("vo",vo);
        return "wait/wait";
    }

    @PostMapping("groupjoiner")
    public String groupjoiner(GroupVO vo){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            vo.setLogin_id(memberVO.getLogin_id());
            if(groupService.aprv_null(memberVO.getLogin_id()) != 0){
                return "redirect:/group/wait";
            }
        }

        groupService.groupjoin(vo);

        return "redirect:/group/wait";
    }

    @PostMapping("waitdel")
    public String waitdel(GroupVO vo, RedirectAttributes ra){
        String loginId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            loginId = memberVO.getLogin_id();

        }

        if(groupService.waitdel(loginId)==1) ra.addFlashAttribute("msg","가입승인이 취소되었습니다.");
        else ra.addFlashAttribute("msg", "가입승인 취소가 실패했습니다. 관리자에게 문의하세요. 1566-6666");

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
