package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.group.GroupService;
import com.project.groups.membersZ.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/apps")
public class ApiController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @GetMapping("/mych")
    public String mych(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            model.addAttribute("membervo",memberVO);
            MemberVO vo = groupService.ckyou(memberVO.getLogin_id());
            String you = vo.getYoutube_id();
            if(you.equals("n")) return "youtube/getid";
            else model.addAttribute("you", you);
        }
        return "youtube/myyoutube";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("youtube_id")String youtube_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            MemberVO vo = MemberVO.builder().login_id(memberVO.getLogin_id()).youtube_id(youtube_id).build();

            groupService.youupdate(vo);
        }
        return "redirect:/apps/mych";
    }
}
