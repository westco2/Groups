package com.project.groups.controller;

import com.project.groups.command.ApprovalFileVO;
import com.project.groups.command.MemberVO;

import com.project.groups.command.UploadVO;
import com.project.groups.membersZ.service.CustomUserDetails;
import com.project.groups.membersZ.service.MembersZService;
import com.project.groups.membersZ.service.UserDetailsServiceImpl;
import com.project.groups.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller //로그인, 회원가입에 관한 컨트롤러들
public class MemberZController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    @Qualifier("MembersZService")
    private MembersZService membersZService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    

    public MemberZController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/memberreg") //회원가입에서 입력된 값을 받음
    public String memberreg(MemberVO memberVO){
        String pswd = passwordEncoder.encode(memberVO.getPswd());
        memberVO.setPswd(pswd);
        if(memberVO.getRole().equals("ROLE_STUDENT")){
            membersZService.memberreg(memberVO);
            return "redirect:/login";
        }else {
            MultipartFile file = memberVO.getFile();
            try {
                //파일 업로드
                ApprovalFileVO approvalFileVO = uploadFile(file);
                approvalFileVO.setLogin_id(memberVO.getLogin_id());
                if (membersZService.memberreg(memberVO)) {//boolean 으로 회원가입 성공 확인
                    if (membersZService.approvalfile(approvalFileVO)) {
                        System.out.println("회원가입 신청 완료!");
                        return "redirect:/login";
                    }
                }

                return "redirect:/memberZ/choiceMemTeacher"; //실패시 다시 회원가입 페이지

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/error"; //파일 업로드중 오류 발생했을때 처리
            }
        }
    }

    private ApprovalFileVO uploadFile(MultipartFile file) throws IOException{
        if(file.isEmpty()){
            throw new IllegalArgumentException("업로드할 파일이 비어있습니다.");
        }
        String uploadDir = UUID.randomUUID().toString() ; //uuid
        String fileName =  file.getOriginalFilename(); //파일이름
        String filePath = uploadDir + File.separator + fileName; //파일경로

        String objectKey = uploadDir + "_" + fileName;

        byte[] filedata = new byte[0];//파일데이터
        try {
            filedata = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        s3Service.putS3Object(objectKey,filedata);

        ApprovalFileVO approvalFileVO = ApprovalFileVO.builder()
                .filename(fileName)
                .filepath(filePath)
                .upload_dir(uploadDir)
                .build();

        return approvalFileVO;
    }

    @ResponseBody
    @PostMapping("/memberidcheck")  //회원가입 id 중복 체크
    public int memberidcheck(@RequestParam("id") String memberidcheck){
        int checkingid = membersZService.memberidcheck(memberidcheck);
        return checkingid;
                          }

    @ResponseBody
    @PostMapping("/membernickcheck") //회원가입 닉네임 중복 체크
    public int membernickcheck(@RequestParam("nick") String membernickcheck){
        int checkingnick = membersZService.membernickcheck(membernickcheck);
        return checkingnick;
    }
    //컴파일러 컨트롤러
    @GetMapping("/javacompilerZ/CompilerTestZ")
    public String CompilerTestZ(){
        return ("/javacompilerZ/CompilerTestZ");
    }

    //로그인 컨트롤 사실 필요가 없다.
    @PostMapping("/login")
    public void loginchecking(
        @RequestParam("username") String login_id,
        @RequestParam("password") String password) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(login_id);
            System.out.println("컨트롤러 UserDetails = " + userDetails);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);

            Authentication authentication = authenticationManager.authenticate(token);

            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("오류발생");
            }

    }

//    @GetMapping("/memberZ/memberIdentification")
//    public String memberIdentification(){
//        return ("memberZ/memberIdentification");
//    }

    @GetMapping("/memberZ/choiceMember")
    public String choiceMember(){
        return ("memberZ/choiceMember");
    }
    @GetMapping("/memberZ/choiceMemTeacher")
    public String choiceMemTeacher(){
        return ("memberZ/choiceMemTeacher");
    }

    @GetMapping("/memberZ/choiceMemStudent")
    public String choiceMemStudent(){
        return ("memberZ/choiceMemStudent");
    }
}
