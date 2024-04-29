package com.project.groups.membersZ.service;

import com.project.groups.command.ApprovalFileVO;
import com.project.groups.command.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembersZMapper {
    public boolean memberreg(MemberVO memberVO);

    public Integer memberidcheck(String memberidcheck);

    public Integer membernickcheck(String membernickcheck);
    public String memberlogin(String login_id);
    public MemberVO memberinf(String login_id);

    public boolean approvalfile(ApprovalFileVO approvalFileVO);
    public List<MemberVO> getuserlist();
}
