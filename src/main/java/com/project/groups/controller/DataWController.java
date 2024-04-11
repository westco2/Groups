package com.project.groups.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.groups.command.DataVO;
import com.project.groups.command.QnaVO;
import com.project.groups.dataW.service.DataWService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;

@Controller
@RequestMapping("/dataW")
public class DataWController {

	@Autowired
	@Qualifier("DataWService")
	private DataWService dataWService;
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWBoardT")
	public String dataWBoardT(Model model, Criteria cri) {
		ArrayList<DataVO> datavo = dataWService.getList(cri);
		int total = dataWService.getTotal();
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("datavo", datavo);
		model.addAttribute("PageVO", pageVO);
		return "dataW/dataWBoardT";
	}
	
	@GetMapping("/dataWBoardS")
	public String dataWBoardS(Model model, Criteria cri) {
		ArrayList<DataVO> datavo = dataWService.getList(cri);
		int total = dataWService.getTotal();
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("datavo", datavo);
		model.addAttribute("PageVO", pageVO);
		return "dataW/dataWBoardS";
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWRegist")
	public String dataWRegist() {
		return "dataW/dataWRegist";
	}
	
	@PostMapping("/InsertWForm")
	public String InsertWForm(DataVO vo, RedirectAttributes re) {
		int result = dataWService.regist(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		}
		else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/dataW/dataWBoardT";
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWDetail")
	public String dataWDetail(@RequestParam("GNUMBER") int GNUMBER,
							  Model model) {
		DataVO vo = dataWService.getDetail(GNUMBER);
		model.addAttribute("vo", vo);
		return "dataW/dataWDetail";
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWUpdate")
	public String dataWUpdate(@RequestParam("GNUMBER") int GNUMBER,
							  Model model) {
		DataVO vo = dataWService.getDetail(GNUMBER);
		model.addAttribute("vo", vo);
		return "dataW/dataWUpdate";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(DataVO vo, RedirectAttributes re) {
		int result = dataWService.update(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		} else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/dataW/dataWBoardT";
	}
	
	//////////////////////////////////////////////////
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("GNUMBER") int GNUMBER) {
		dataWService.delete(GNUMBER);
		return "redirect:/dataW/dataWBoard";
	}
	
	//////////////////////////////////////////////////
}