package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;

public interface MembersZService {
    public boolean memberreg(MemberVO memberVO); //회원 가입
    public int memberidcheck(String memberidcheck); //아이디중복체크
    public int membernickcheck(String membernickcheck); //닉네임중복체크
    public boolean memberlog(MemberVO memberVO); //로그인
}
