package com.project.groups.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.groups.command.MemberVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.membersZ.service.CustomUserDetails;
//import com.project.groups.qnaW.service.QnaWService;
//import com.project.groups.util.Criteria;
//import com.project.groups.util.PageVO;
import com.project.groups.qnaW.service.QnaWService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;

@Controller
@RequestMapping("/qnaW")
public class QnaWController {

	@Autowired
	@Qualifier("QnaWService")
	private QnaWService qnaWService;
	
	@GetMapping("/qnaWBoard") //로그인한 id와 같은 작성 질문만 올라오게 만들어져있음
	public String qnaWBoard(Model model, Criteria cri) {
		String login = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            System.out.println(memberVO.getRole());
            if(memberVO.getRole().equals("ROLE_TEACHER")||
               memberVO.getRole().equals("ROLE_TEACHER_BASICTIER")||
               memberVO.getRole().equals("ROLE_TEACHER_MASTERTIER")) {
            	System.out.println("실행");
            	model.addAttribute("qnavo", qnaWService.getList(cri, memberVO.getLogin_id()));
            }else if(memberVO.getRole().equals("ROLE_STUDENT")) model.addAttribute("qnavo", qnaWService.getList2(cri, memberVO.getLogin_id()));
            int total = qnaWService.getTotal(login, cri);
    		PageVO pageVO = new PageVO(cri, total);
    		model.addAttribute("total", total);
            model.addAttribute("membervo",memberVO);
        	System.out.println(memberVO);
        }
		
		return "qnaW/qnaWBoard";
	}
	
	@GetMapping("/qnaWRegist")
	public String qnaWRegist(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
            model.addAttribute("group", qnaWService.getgroupinfo(memberVO.getLogin_id())); 
        }
		return "qnaW/qnaWRegist";
	}
	
	@PostMapping("/InsertWForm")
	public String InsertWForm(QnaVO vo, RedirectAttributes re) {
		int result = qnaWService.regist(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		}
		else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/qnaW/qnaWBoard";
	}	
	
	@GetMapping("/qnaWDetail")
	public String getDetail(@RequestParam("qnumber") int qnumber,
			  				 Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            System.out.println(memberVO.getRole());
            
        	model.addAttribute("membervo",memberVO);
        }
		QnaVO vo = qnaWService.getDetail(qnumber);
		model.addAttribute("vo", vo);
		return "qnaW/qnaWDetail";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(QnaVO vo, RedirectAttributes re) {
		int result = qnaWService.update(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "답변이 완료되었습니다.");
		} else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/qnaW/qnaWBoard";
	}
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("qnumber") int qnumber) {
		qnaWService.delete(qnumber);
		return "redirect:/qnaW/qnaWBoard";
	}
}