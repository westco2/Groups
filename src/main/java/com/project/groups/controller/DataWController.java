package com.project.groups.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.groups.command.DataVO;
import com.project.groups.command.MemberVO;
import com.project.groups.command.UploadVO;
import com.project.groups.dataW.service.DataWService;
import com.project.groups.membersZ.service.CustomUserDetails;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;

@Controller
@RequestMapping("/dataW")
public class DataWController {

	//////////////////////////////////////////////////
	//파일 업로드에 필요한 값들
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	public String makeFolder() {
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		File file = new File(uploadPath + "/" + filepath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		return filepath;
	}
	
	//////////////////////////////////////////////////
	
	@Autowired
	@Qualifier("DataWService")
	private DataWService dataWService;
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWBoardT")
	public String dataWBoardT(Model model, Criteria cri) {
		String login= null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
            
           login = memberVO.getLogin_id();
        }
		model.addAttribute("names",dataWService.getgroupname(login));
		
		ArrayList<DataVO> datavo = dataWService.getList(login,cri);
		int total = dataWService.getTotal(login, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("datavo", datavo);
		model.addAttribute("pageVO", pageVO);
		System.out.println(datavo);
		return "dataW/dataWBoardT";
	}
	
	@GetMapping("/dataWBoardS")
	public String dataWBoardS(Model model, Criteria cri) {
		
		
		String login= null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
            
           login = memberVO.getLogin_id();
        }
		model.addAttribute("names",dataWService.getgroupname(login));
		
		ArrayList<DataVO> datavo = dataWService.getList2(login,cri);
		int total = dataWService.getTotal(login, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("datavo", datavo);
		model.addAttribute("pageVO", pageVO);
		System.out.println(datavo);
		return "dataW/dataWBoardS";
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWRegist")
	public String dataWRegist(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
            model.addAttribute("names", dataWService.getgroupname(memberVO.getLogin_id()));
           
        }
		return "dataW/dataWRegist";
	}
	
	@PostMapping("/InsertWForm")
	public String InsertWForm(DataVO vo,
							  UploadVO uploadvo,
							  RedirectAttributes re,
							  MultipartHttpServletRequest part
							  ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login_idid = authentication.getName();
        System.out.println("login_idid" + login_idid);
		List<MultipartFile> list = part.getFiles("file");
		vo.setLogin_id(login_idid);
		int result = dataWService.regist(vo,list);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		}
		else {
			re.addFlashAttribute("msg", "등록실패");
		}
		
		
		return "redirect:/dataW/dataWBoardT";
	}
	@ResponseBody
	@GetMapping("/download/{filepath}/{uuid}/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("filepath") String filepath,
										   @PathVariable("uuid") String uuid,
										   @PathVariable("filename") String filename){
		ResponseEntity<byte[]> entity = null;
		try {
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			File file = new File(savePath);
			byte[] arr = FileCopyUtils.copyToByteArray(file);
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Disposition", "attachment; filename=" + filename );
			entity = new ResponseEntity<>(arr, header, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWDetail")
	public String dataWDetail(@RequestParam("gnumber") Integer gnumber,
							  Model model) {
		DataVO vo = dataWService.getDetail(gnumber);
		model.addAttribute("list",dataWService.getfile(gnumber));
		model.addAttribute("vo", vo);
		return "dataW/dataWDetail";
	}
	
	//////////////////////////////////////////////////
	
	@GetMapping("/dataWUpdate")
	public String dataWUpdate(@RequestParam("gnumber") Integer gnumber,
							  Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
           
        }
		DataVO vo = dataWService.getDetail(gnumber);
		model.addAttribute("vo", vo);
		return "dataW/dataWUpdate";
	}
	
	@PostMapping("/updateForm")
	public String updateForm(DataVO vo,
							 UploadVO uploadvo,
							 RedirectAttributes re,
							 MultipartHttpServletRequest part
							 ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login_idid = authentication.getName();
		int result = dataWService.update(vo);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		} else {
			re.addFlashAttribute("msg", "등록실패");
		}		List<MultipartFile> list = part.getFiles("file");
		
		for(MultipartFile file : list) {
			String filename = file.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String filepath = makeFolder();
			String fileurl = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			//long size = file.getSize();
			
			System.out.println("파일명:" + filename);
			System.out.println("폴더명:" + filepath);
			System.out.println("업로드할경로:" + fileurl);
			System.out.println("랜덤이름:" + uuid);
			//System.out.println("파일사이즈:" + size);
		
			uploadvo.setFilename(filename);
			uploadvo.setFilepath(filepath);
			uploadvo.setFileurl(fileurl);
			uploadvo.setUuid(uuid);
			
			try {
				File saveFile = new File(fileurl);
				file.transferTo(saveFile);
				
				//dataWService.upload(uploadvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/dataW/dataWBoardT";
	}
	
	//////////////////////////////////////////////////
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("gnumber") Integer gnumber) {
		dataWService.delete(gnumber);
		return "redirect:/dataW/dataWBoard";
	}
	
	//////////////////////////////////////////////////
}