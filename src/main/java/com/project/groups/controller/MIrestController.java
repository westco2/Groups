package com.project.groups.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.groups.command.*;
import com.project.groups.group.GroupService;
import com.project.groups.homework.HomeworkService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MIrestController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("homeworkService")
    private HomeworkService homeworkService;

    @PostMapping("/getstdInfo")
    public ResponseEntity<List<GroupVO>> getstdInfo(@RequestBody GroupVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입

        return new ResponseEntity<>(groupService.getstdInfo(vo), headers, HttpStatus.OK);
    }
    @GetMapping("/stddel")
    public String stddel(@RequestParam("login_ids")List<String> login_ids, @RequestParam("group_no") Integer group_no){
        groupService.groupstddel(login_ids,group_no);


        return "success";
    }

    @GetMapping("/getgroupstdInfo")
    public ResponseEntity<List<GroupVO>> getgroupstdInfo(Criteria cri, @RequestParam Integer group_no) {
        PageVO vo = new PageVO(cri, groupService.getstdtotal(cri, group_no));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(groupService.getgroupstdinfo(cri, group_no), headers, HttpStatus.OK);
    }
    @GetMapping("/getgroupstdInfopage")
    public ResponseEntity<PageVO> getgroupstdInfopage(Criteria cri, @RequestParam Integer group_no) {
        PageVO vo = new PageVO(cri, groupService.getstdtotal(cri, group_no));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(vo, headers, HttpStatus.OK);
    }

    @GetMapping("/getgroupdataInfo")
    public ResponseEntity<List<DataVO>> getgroupdataInfo(Criteria cri, @RequestParam Integer group_no) {
        PageVO vo = new PageVO(cri, groupService.getdatainfototal( group_no,cri));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(groupService.getdatainfo(group_no, cri), headers, HttpStatus.OK);
    }
    @GetMapping("/getgroupdataInfopage")
    public ResponseEntity<PageVO> getgroupdataInfopage(Criteria cri, @RequestParam Integer group_no) {
        PageVO vo = new PageVO(cri, groupService.getdatainfototal(group_no, cri));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(vo, headers, HttpStatus.OK);
    }


    //내숙제 세부정보
    @PostMapping("/gethomedetail")
    public ResponseEntity<HomeWorkVO> gethomedetail(@RequestBody Map<String, Integer> requestBody) {
        Integer homework_no = requestBody.get("homework_no");
        // homeworkNo를 사용하여 숙제 상세 정보를 가져온 후 적절한 응답을 반환합니다.

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입
        HomeWorkVO vo = homeworkService.homeworkvoselect(homework_no);

        List<ExVO> exVOList = homeworkService.listexvoselect(homework_no);

        List<TestVO> testVOList = homeworkService.listtestvoselect(homework_no);



        vo.setList_exvo(exVOList);
        vo.setList_testvo(testVOList);
        System.out.println(vo);


        return new ResponseEntity<>(vo, headers, HttpStatus.OK);
    }

    @PostMapping("/getstdlist")
    public ResponseEntity<List<GroupVO>> getstdlist (@RequestBody GroupVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<>(homeworkService.getstdhome(vo),headers,HttpStatus.OK);
    }

    @PostMapping("/submithomework")
    public String submithomework(@RequestBody HomeWorkVO vo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();
        vo.setLogin_id(sessionId);
        Integer score = homeworkService.getscore(vo);
        System.out.println(score);
        if(vo.getScore() == 100){
            if(score != 100){
                System.out.println("실행1");
                homeworkService.updatepoint(vo);
                if(vo.getScore() >= score) homeworkService.updatesubmit(vo);
            }
        }else{
            if(score != 100) {
                System.out.println("실행");
                System.out.println(vo.getScore());
                System.out.println(score);
                if (vo.getScore() >= score) homeworkService.updatesubmit(vo);
            }
        }



        homeworkService.homeworkrecord(vo);



        return "success";
    }


    @PostMapping("/getrecorddetail")
    public ResponseEntity<List<HomeWorkVO>>getrecorddetail(@RequestBody HomeWorkVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입

        return new ResponseEntity<>(homeworkService.getrecord(vo.getHomework_no()), headers, HttpStatus.OK);
    }


    @PostMapping("/gethomeworkinfo")
    public ResponseEntity<List<HomeWorkVO>>gethomeworkinfo(@RequestBody HomeWorkVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입

        return new ResponseEntity<>(homeworkService.getstdhomeck(vo), headers, HttpStatus.OK);
    }


    @PostMapping("/getctg")
    public ResponseEntity<List<CategoryVO>>getctg(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입

        return new ResponseEntity<>(homeworkService.getcategory(sessionId), headers, HttpStatus.OK);
    }

    @PostMapping("/regctg")
    public String regctg(@RequestBody CategoryVO vo){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = authentication.getName();
        vo.setLogin_id(sessionId);

        homeworkService.regcategory(vo);

        return "success";
    }

    @PostMapping("/deljoins")
    public String deljoin(@RequestBody GroupVO vo){
        groupService.waitdel(vo.getLogin_id());
        return "success";
    }







}
