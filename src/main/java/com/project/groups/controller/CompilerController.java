package com.project.groups.controller;

import com.project.groups.compilerZ.CodeRunner;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompilerController {

    @PostMapping("/checkCode")
    public String checkCode(@RequestBody String code) {
        System.out.println("code:" + code);
        JSONObject jsonObject = new JSONObject(code);
        String codestr = jsonObject.getString("code");
        System.out.println("codestr = " + codestr);
        System.out.println("sol : " + CodeRunner.CodeRunnerTest(codestr));
        return CodeRunner.CodeRunnerTest(codestr);
    }
}


