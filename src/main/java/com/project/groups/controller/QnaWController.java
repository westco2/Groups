package com.project.groups.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/qnaWBoard")
	public String qnaWBoard(Model model, Criteria cri) {
		ArrayList<QnaWVO> list = qnaWService.getList(cri);
		 
		
//		int total = qnaWService.getTotal();
//		PageVO pageVO = new PageVO(cri, total);
//		model.addAttribute("list", list);
//		model.addAttribute("pageVO", pageVO);
		
		return "qnaW/qnaWBoard";
	}
	//////////////////////////////////////////////////
	
	@GetMapping("/qnaWDetail")
	public String qnaWDetail() {
		return "qnaW/qnaWDetail";
	}
	
	@PostMapping("/ReplyWForm")
	public String ReplyWForm(QnaWVO vo, RedirectAttributes re) {
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
	
	@GetMapping("/qnaWRegist")
	public String qnaWRegist() {
		return "/qnaW/qnaWRegist";
	}
	
	@PostMapping("/InsertWForm")
	public String InsertWForm(QnaWVO vo, RedirectAttributes re) {
//		int result = qnaWService.regist(vo);
//		if(result == 1) {
//			re.addFlashAttribute("msg", "등록완료");
//		}
//		else {
//			re.addFlashAttribute("msg", "등록실패");
//		}
		return "redirect:/qnaWBoard";
	}	
	////////////////////////////////////////////////



}