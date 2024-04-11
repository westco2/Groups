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

import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getPrincipal());
//		System.out.println(authentication.getDetails());
//		System.out.println(authentication.getName());
//		System.out.println(authentication.getAuthorities());
	
		List<QnaWVO> qnawvo = qnaWService.getList(cri);
//		System.out.println(qnawvo.toString());
//		System.out.println(qnawvo.get(0));
		List<QnaVO> qnavo = qnaWService.getList2(cri);
//		System.out.println(qnavo);
		List<QnaWVO> currentUserQnaWVOList = new ArrayList<>();
		for(int a=0; a<qnawvo.size(); a++) {
			QnaWVO qnawvoob = qnawvo.get(a);
			qnawvoob.setList(qnavo.get(a));
			qnawvo.set(a, qnawvoob);
			if(qnawvo.get(a).getLOGIN_ID().equals(authentication.getName())) {
				currentUserQnaWVOList.add(qnawvoob);
			}
		}
//		System.out.println(currentUserQnaWVOList.toString());
//		int total = qnaWService.getTotal();
//		PageVO pageVO = new PageVO(cri, total);
//		model.addAttribute("pageVO", pageVO);
		model.addAttribute("qnawvo", currentUserQnaWVOList);
//		System.out.println(qnawvo.get(0).getLOGIN_ID());
		
		return "qnaW/qnaWBoard";
	}
	//////////////////////////////////////////////////
	
	@GetMapping("/qnaWRegist")
	public String qnaWRegist() {
		return "/qnaW/qnaWRegist";
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
	
	//////////////////////////////////////////////////
	
	@GetMapping("/qnaWDetail")
	public String getDetail(@RequestParam("QNUMBER") int QNUMBER,
			  				 Model model) {
		QnaVO vo = qnaWService.getDetail(QNUMBER);
		model.addAttribute("vo", vo);
		return "qnaW/qnaWDetail";
	}
	
	//////////////////////////////////////////////////
	
	@PostMapping("/updateForm")
	public String updateForm(QnaVO vo, RedirectAttributes re) {
		int result = qnaWService.update(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		} else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/qnaW/qnaWBoard";
	}
	
	//////////////////////////////////////////////////
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("QNUMBER") int QNUMBER) {
		qnaWService.delete(QNUMBER);
		return "redirect:/qnaW/qnaWBoard";
	}
	
	//////////////////////////////////////////////////
	
	@PostMapping("/ReplyWForm")
	public String ReplyWForm(QnaVO vo, RedirectAttributes re) {
//		int result = qnaWService.reply(vo);
//		if(result == 1) {
//			re.addFlashAttribute("msg", "등록완료");
//		}
//		else {
//			re.addFlashAttribute("msg", "등록실패");
//		}
		return "redirect:/qnaWDetail";
	}
	
	////////////////////////////////////////////////
	




}