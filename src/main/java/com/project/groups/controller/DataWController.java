package com.project.groups.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dataW")
public class DataWController {

//	@Autowired
//	@Qualifier("DataWService")
//	private DataWService dataWService;
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWBoard")
	public String dataWBoard() {
		
		return "dataW/dataWBoard";
	}
	
	@GetMapping("dataWDetail")
	public String dataWDetail() {
		
		return "dataW/dataWDetail";
	}
	
	@GetMapping("dataWRegist")
	public String dataWRegist() {
		
		return "dataW/dataWRegist";
	}
	
	@GetMapping("dataWUpdate")
	public String dataWUpdate() {
		
		return "dataW/dataWUpdate";
	}
	
}
