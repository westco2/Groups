package com.project.groups.controller;

import com.project.groups.command.ExVO;
import com.project.groups.command.HomeWorkVO;
import com.project.groups.command.TestVO;
import com.project.groups.homework.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;

    @GetMapping("/homeworklist")
    public String hoemworklist(){
        return "homework/homeworklist";
    }

    @GetMapping("/homeworkreg")
    public String homeworkreg(){
        return "homework/homeworkreg";
    }



    /* 학생 */

    @GetMapping("/myhomework")
    public String myhomework(){
        return "homework/myhomework";
    }

    @GetMapping("/myhomeworkdetail/{homework_no}")
    public String myhomeworkdetail(@PathVariable("homework_no") Integer homework_no , Model model){
        //homework_no 에 해당하는 homework 테이블
        HomeWorkVO homeworkVO = homeworkService.homeworkvoselect(homework_no);
        System.out.println("homework_no = " + homework_no);
        System.out.println("homeworkVO = " + homeworkVO);
        //ex테이블
        List<ExVO> exVOList = homeworkService.listexvoselect(homework_no);
        System.out.println("exVOList = " + exVOList);

        //test테이블
        List<TestVO> testVOList = homeworkService.listtestvoselect(homework_no);
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


}


