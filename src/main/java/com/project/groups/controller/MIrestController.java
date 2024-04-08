package com.project.groups.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.group.GroupService;
import com.project.groups.util.Criteria;
import com.project.groups.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MIrestController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

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
    public ResponseEntity<List<GroupVO>> getstdInfo(Criteria cri, @RequestParam Integer group_no) {
        PageVO vo = new PageVO(cri, groupService.getstdtotal(cri, group_no));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(groupService.getgroupstdinfo(cri, group_no), headers, HttpStatus.OK);
    }




}
