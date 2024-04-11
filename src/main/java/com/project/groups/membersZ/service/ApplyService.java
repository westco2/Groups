package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;

import java.util.List;

public interface ApplyService {

    List<MemberVO> applymemberlist();

    void approveteacher(String loginId);
}
