package com.project.groups.controller;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/fileupload")
public class UploadWController {
	
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
	
	@GetMapping("/upload")
	public String upload() {
		return "fileupload/upload";
	}
	
	@PostMapping("/upload_ok")
	public String upload_ok(MultipartHttpServletRequest part) {
	
	List<MultipartFile> list = part.getFiles("file");
	
	for(MultipartFile file : list) {
		String originName = file.getOriginalFilename();
		originName = originName.substring(originName.lastIndexOf("//") + 1);
		
		long size = file.getSize();
		String uuid = UUID.randomUUID().toString();
		String filepath = makeFolder(); //yyyyMMdd
		String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
		System.out.println("첨부파일명 : " + originName); //원본파일명 DB저장
		System.out.println("폴더명 : " + filepath); //폴더명 DB저장
		System.out.println("랜덤이름 : " + uuid); //랜덤한 이름 DB저장
		System.out.println("업로드할경로 : " + savePath);
		try {
			File saveFile = new File(savePath);
			file.transferTo(saveFile); //업로드
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	return "fileupload/upload_ok";
	}
}