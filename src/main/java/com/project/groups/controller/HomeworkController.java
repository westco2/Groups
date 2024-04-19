package com.project.groups.controller;

import com.project.groups.command.*;
import com.project.groups.homework.HomeworkService;
import com.project.groups.membersZ.service.CustomUserDetails;
import com.project.groups.util.Criteria;
import com.project.groups.util.EncryptionUtils;
import com.project.groups.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;

    @GetMapping("/homeworklist")
    public String hoemworklist(Model model, Criteria cri){

        String sessionId =null;
        String tier = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            tier = memberVO.getTier();
            sessionId = memberVO.getLogin_id();

        }
        System.out.println(tier);
        if (tier.equals("FREETIER")) {
            String warningMessage = "FREETIER 회원입니다. 구독등급을 업그레이드 하세요";
            model.addAttribute("warningMessage", warningMessage);
            // /homeworklist로 리다이렉트
        }
        model.addAttribute("cat", homeworkService.getcategory(sessionId));
        int total = homeworkService.gethomeworktotal(sessionId, cri);
        PageVO vo = new PageVO(cri,total);
        model.addAttribute("total", total);
        model.addAttribute("names", homeworkService.getgroupnames(sessionId));
        model.addAttribute("home",homeworkService.gethomeworklist(sessionId, cri));
        model.addAttribute("pageVO",vo);
        return "homework/homeworklist";
    }

    @GetMapping("/homeworkreg")
    public String homeworkreg(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            System.out.println("MemberVO: " + memberVO);
            model.addAttribute("membervo",memberVO);
        }
        return "homework/homeworkreg";
    }



    /* 학생 */

    @GetMapping("/myhomework")
    public String myhomework(Model model, Criteria cri){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();
        int total = homeworkService.getmyhomeworktotal(sessionId, cri);
        PageVO vo = new PageVO(cri,total);

        List<HomeWorkVO> list = homeworkService.getmyhomeworklist(sessionId, cri);
        list.forEach(a -> {
            String encryptedHomeworkNo = EncryptionUtils.encrypt(a.getHomework_no() + "");
            if (encryptedHomeworkNo != null) {
                a.setHomework_non(encryptedHomeworkNo);
            } else {
                // 암호화 실패 시 처리할 로직을 여기에 추가하세요
            }
        });
        System.out.println(list);
        model.addAttribute("total",total);
        model.addAttribute("home",list);
        model.addAttribute("pageVO",vo);
        return "homework/myhomework";
    }

    @GetMapping("/myhomeworkdetail")
    public String myhomeworkdetail(@RequestParam("homework_no") String homework_no , Model model){

        Integer homework_noo =  Integer.parseInt(Objects.requireNonNull(EncryptionUtils.decrypt(homework_no)));
        //homework_no 에 해당하는 homework 테이블
        HomeWorkVO homeworkVO = homeworkService.homeworkvoselect(homework_noo);
        System.out.println("homework_no = " + homework_noo);
        System.out.println("homeworkVO = " + homeworkVO);
        //ex테이블
        List<ExVO> exVOList = homeworkService.listexvoselect(homework_noo);
        System.out.println("exVOList = " + exVOList);

        //test테이블
        List<TestVO> testVOList = homeworkService.listtestvoselect(homework_noo);
        System.out.println("testVOList = " + testVOList);

        // Model에 속성 추가
        model.addAttribute("vo", homeworkVO);
        model.addAttribute("vo2", exVOList);
        model.addAttribute("vo3", testVOList);

        return "homework/myhomeworkdetail";
    }

    /* 선생 숙제 등록 - 현준 */
    @PostMapping("/homeworkregForm")
    public String homeworkregForm(HomeWorkVO homeworkvo, HttpServletRequest request, RedirectAttributes ra){

        List<ExVO> list_exvo = new ArrayList<>();
        String[] inputs = request.getParameterValues("input[]");
        String[] outputs = request.getParameterValues("output[]");
        String[] ex_cts = request.getParameterValues("ex_ct[]");
        if (inputs != null && outputs != null && ex_cts != null) {
            for (int i = 0; i < inputs.length; i++) {
                ExVO exVO = ExVO.builder()
                        .input(inputs[i])
                        .output(outputs[i])
                        .ex_ct(ex_cts[i])
                        .build();
                list_exvo.add(exVO);
            }
        }
        homeworkvo.setList_exvo(list_exvo);

        List<TestVO> list_testvo = new ArrayList<>();
        String[] test_inputs = request.getParameterValues("test_input[]");
        String[] test_outputs = request.getParameterValues("test_output[]");
        if (test_inputs != null && test_outputs != null) {
            for (int i = 0; i < test_inputs.length; i++) {
                TestVO testVO = TestVO.builder()
                        .test_input(test_inputs[i])
                        .test_output(test_outputs[i])
                        .build();
                list_testvo.add(testVO);
            }
        }
        homeworkvo.setList_testvo(list_testvo);

        System.out.println("homeworkvo = " + homeworkvo); //homeWorkVO 값 확인
        if (homeworkService.homeworkregForm(homeworkvo) == 1 ) {
            ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        }


        return "redirect:/homework/homeworklist";
    }

    /* 선생 숙제 전송 */
    @PostMapping("/homeworksend")
    public String homeworksend(@RequestParam("login_id") List<String> ids, @RequestParam("homework_no")Integer homework_no, @RequestParam("homework_enddate") Date homework_enddate, RedirectAttributes ra){
        homeworkService.sendhomework(ids,homework_no,homework_enddate);
        ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        return "redirect:/homework/homeworklist";
    }


    /* 카테고리 */
    @PostMapping("/regcategory")
    public String regcategory(CategoryVO vo, RedirectAttributes ra){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            vo.setLogin_id(memberVO.getLogin_id());

        }
        if (homeworkService.regcategory(vo) == 1 ) {
            ra.addFlashAttribute("msg","등록이 완료 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        }
        

        return "redirect:/homework/homeworklist";
    }
    @PostMapping("/deletecategory")
    public String deletecategory(CategoryVO vo, RedirectAttributes ra){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            vo.setLogin_id(memberVO.getLogin_id());

        }
        System.out.println(vo);
        if (homeworkService.deletecategory(vo) == 1 ) {
            ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "삭제에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        }


        return "redirect:/homework/homeworklist";
    }


    @PostMapping("homeworkupdate")
    public String homeworkupdate(HomeWorkVO vo, Model model){
        model.addAttribute("vo", homeworkService.gethwinfo(vo));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            MemberVO memberVO = userDetails.getMemberVO();
            vo.setLogin_id(memberVO.getLogin_id());
            model.addAttribute("membervo", memberVO);

        }
        return "homework/homeworkupdate";
    }

    @PostMapping("homeworkdelete")
    public String homeworkupdate(HomeWorkVO vo, RedirectAttributes ra){

        if (homeworkService.hwidel(vo) == 1 ) {
            ra.addFlashAttribute("msg","정상적으로 처리되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "삭제에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        }


        return "redirect:/homework/homeworklist";
    }

    @PostMapping("homeworkupdateForm")
    public String homeworkupdateForm(HomeWorkVO vo, RedirectAttributes ra){
        if (homeworkService.homeworkup(vo) == 1 ) {
            ra.addFlashAttribute("msg","수정이 완료 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "삭제에 실패했습니다. 관리자에게 문의하세요. 1566-6666");
        }
        return "redirect:/homework/homeworklist";
    }


}


