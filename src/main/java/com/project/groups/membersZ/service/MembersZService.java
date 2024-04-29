package com.project.groups.membersZ.service;

import com.project.groups.command.ApprovalFileVO;
import com.project.groups.command.MemberVO;

import java.util.List;

public interface MembersZService {
    public boolean memberreg(MemberVO memberVO); //회원 가입
    public int memberidcheck(String memberidcheck); //아이디중복체크
    public int membernickcheck(String membernickcheck); //닉네임중복체크
    public String memberlogin(String login_id); //로그인
    public MemberVO memberinf(String login_id);

    public boolean approvalfile(ApprovalFileVO approvalFileVO); //증명파일 테이블 저장
    public List<MemberVO> getuserlist();
}
