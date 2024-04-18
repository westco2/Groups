package com.project.groups.controller;

import com.project.groups.command.MemberVO;
import com.project.groups.membersZ.service.ApplyService;
import com.project.groups.membersZ.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/memberZ")
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @GetMapping("/applymember")
    public String applymember(Model model){
        List<MemberVO> applymemberlist = applyService.applymemberlist();
        System.out.println("applymemberlist = " + applymemberlist);
        model.addAttribute("amlist", applymemberlist);
        System.out.println("model = " + model);
        return "memberZ/applymember";
    }

    @PostMapping("/approvemember")
    public String approvemember(@RequestParam("loginId") String loginId){
        System.out.println("loginId = " + loginId);
        applyService.approveteacher(loginId);
        return "redirect:/memberZ/applymember";
    }



    @GetMapping("/tierchoiceZ")
    public String tierchoiceZ(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
        }
        return "memberZ/tierchoiceZ";
    }

    // 파일이 저장된 디렉토리 경로 설정
    private static final String UPLOAD_DIR = "C:\\Users\\hyunj\\Desktop\\upload";
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        // 파일 경로 생성
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        Resource resource = null;
        try {
            // 파일을 Resource로 읽어들임
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // 다운로드할 파일의 MIME 타입 설정
        String contentType = null;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Content-Disposition 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

}
