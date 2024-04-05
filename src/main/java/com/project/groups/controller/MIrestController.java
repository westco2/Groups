package com.project.groups.controller;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MIrestController {

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @PostMapping("/getstdInfo")
    public ResponseEntity<List<MemberVO>> getstdInfo(@RequestBody GroupVO vo){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json"); //내가 보내는 컨텐츠 타입

        return new ResponseEntity<>(groupService.getstdInfo(vo), headers, HttpStatus.OK);
    }
}
