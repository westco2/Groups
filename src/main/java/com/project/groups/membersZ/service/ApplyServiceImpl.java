package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "applyService")
public class ApplyServiceImpl implements ApplyService{

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public List<MemberVO> applymemberlist() {
        return applyMapper.applymemberlist();
    }

    @Override
    public void approveteacher(String loginId){
        applyMapper.approveteacher(loginId);
    };
}
