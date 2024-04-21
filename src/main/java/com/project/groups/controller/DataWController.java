package com.project.groups.controller;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.project.groups.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import org.springframework.web.util.UriUtils;

@Controller
@RequestMapping("/dataW")
public class DataWController {
	
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
	
	@Autowired
	@Qualifier("DataWService")
	private DataWService dataWService;

	@Autowired
	private S3Service s3Service;
	
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
		
		ArrayList<DataVO> datavo = dataWService.getList(login, cri);
		int total = dataWService.getTotal(login, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
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
		
		ArrayList<DataVO> datavo = dataWService.getList2(login, cri);
		int total = dataWService.getTotal(login, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("datavo", datavo);
		model.addAttribute("pageVO", pageVO);
		System.out.println(datavo);
		return "dataW/dataWBoardS";
	}
	
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
							  ) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login_idid = authentication.getName();
        System.out.println("login_idid" + login_idid);
		List<MultipartFile> list = part.getFiles("file");
		vo.setLogin_id(login_idid);
		List<UploadVO> volist = new ArrayList<>();
		list.forEach(file -> {
			String filename = file.getOriginalFilename(); //파일명
			filename = filename.substring(filename.lastIndexOf("\\") + 1);

			String uuid = UUID.randomUUID().toString();
			String objectKey =  uuid + "_" + filename;
			//중복으로 올라오는 파일을 피하고싶으면, UUID객체로 랜덤값
			byte[] filedata = new byte[0];//파일데이터
			try {
				filedata = file.getBytes();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			volist.add(UploadVO.builder().fileurl(objectKey).filename(filename).uuid(uuid).login_id(vo.getLogin_id()).build());

			s3Service.putS3Object(objectKey, filedata);
		});

		int result = dataWService.regist(vo, volist);
		if(result == 1) {
			re.addFlashAttribute("msg", "등록완료");
		}
		else {
			re.addFlashAttribute("msg", "등록실패");
		}
		return "redirect:/dataW/dataWBoardT";
	}
	
	@ResponseBody
	@GetMapping("/download/{uuid}/{filename}")
	public ResponseEntity<byte[]> download(@PathVariable("uuid") String uuid,
										   @PathVariable("filename") String filename) {
		System.out.println(uuid);
		System.out.println(filename);
		String key = uuid + "_" + filename;
		try {
			// 파일 다운로드를 위한 작업 수행
			byte[] fileBytes = s3Service.getObjectBytes(key, filename);

			// 파일명 URL 인코딩
			String encodedFilename = UriUtils.encode(filename, StandardCharsets.UTF_8);

			// 파일 다운로드 응답 생성
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", encodedFilename); // 다운로드할 파일의 인코딩된 이름 설정
			headers.setContentLength(fileBytes.length);

			return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dataWDetail")
	public String dataWDetail(@RequestParam("gnumber") Integer gnumber,
							  Model model) {
		DataVO vo = dataWService.getDetail(gnumber);
		model.addAttribute("list", dataWService.getfile(gnumber));
		model.addAttribute("vo", vo);
		return "dataW/dataWDetail";
	}
	
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
		model.addAttribute("list", dataWService.getfile(gnumber));
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
			
			System.out.println("파일명:" + filename);
			System.out.println("폴더명:" + filepath);
			System.out.println("업로드할경로:" + fileurl);
			System.out.println("랜덤이름:" + uuid);
		
			uploadvo.setFilename(filename);
			uploadvo.setFilepath(filepath);
			uploadvo.setFileurl(fileurl);
			uploadvo.setUuid(uuid);
			
			try {
				File saveFile = new File(fileurl);
				file.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/dataW/dataWBoardT";
	}
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("gnumber") Integer gnumber) {
		dataWService.delete(gnumber); //수업자료 게시글 삭제
		dataWService.deletefileupload(gnumber); //수업자료 게시글과 연동된 첨부파일들 삭제
		return "redirect:/dataW/dataWBoardT";
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public Map<String, Object> deleteFile(@RequestBody Map<String, String> requestData){
		Map<String, Object> resultMap = new HashMap<>();
		String filename = requestData.get("filename");
		try {
			dataWService.deleteFile(filename);
			resultMap.put("success",  true);
		} catch (Exception e) {
			resultMap.put("success", false);
			e.printStackTrace();
		}
		return resultMap;
	}
}