package com.project.groups.controller;

import com.project.groups.command.CodeVO;
import com.project.groups.compilerZ.CodeRunner;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompilerController {

    @PostMapping("/checkCode")
    public ResponseEntity<String> checkCode(@RequestBody CodeVO vo) {
        System.out.println(vo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        ResponseEntity entity = new ResponseEntity<>(CodeRunner.CodeRunnerTest(vo.getCode(), vo.getInput(),vo.getAnswer(), vo.getTime()),headers ,HttpStatus.OK);

        System.out.println(entity);

        return entity;
    }
}


