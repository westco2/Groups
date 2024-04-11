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
	
	//업로드경로
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//날짜폴더만드는 함수
	public String makeFolder() {
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		File file = new File(uploadPath + "/" + filepath);
		if(file.exists() == false) { //해당파일이 있으면 true, 없으면 false
			file.mkdirs();
		}
		return filepath;
	}
	
	//업로드화면
	@GetMapping("/upload")
	public String upload() {
		return "fileupload/upload";
	}
	
	//업로드요청
	@PostMapping("/upload_ok")
	public String upload_ok(MultipartHttpServletRequest part) {
	
	List<MultipartFile> list = part.getFiles("file");
	
	for(MultipartFile file : list) {
		//첨부파일명
		String originName = file.getOriginalFilename();
		//브라우저별로 첨부파일의 경로가 포함되서 올라오는 경우도 있기에.
		originName = originName.substring(originName.lastIndexOf("//") + 1);
		
		//첨부파일사이즈
		long size = file.getSize();
		//동일한 첨부파일로 업로드되면, 기존파일에 덮어쓰기가 되기때문에,
		//랜덤한 이름으로 파일명칭변경
		String uuid = UUID.randomUUID().toString();
		
		//날짜별로 폴더생성
		String filepath = makeFolder(); //yyyyMMdd
		
		//업로드할 경로
		String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + originName;
		
		System.out.println("첨부파일명 : " + originName); //원본파일명 DB저장
		System.out.println("폴더명 : " + filepath); //폴더명 DB저장
		System.out.println("랜덤이름 : " + uuid); //랜덤한 이름 DB저장
		System.out.println("업로드할경로 : " + savePath);
		//System.out.println("파일사이즈 : " + size);
		
		try {
			File saveFile = new File(savePath);
			file.transferTo(saveFile); //업로드
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	return "fileupload/upload_ok";
	//////////////////////////////////////////////
	}
	
}