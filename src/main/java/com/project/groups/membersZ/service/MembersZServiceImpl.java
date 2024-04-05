package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "MembersZService")
public class MembersZServiceImpl implements MembersZService {

    @Autowired
    private MembersZMapper membersZMapper;

    @Override
    public boolean memberreg(MemberVO memberVO) {

        return membersZMapper.memberreg(memberVO);
    }

    @Override
    public int memberidcheck(String memberidcheck) {
        return membersZMapper.memberidcheck(memberidcheck);
    }

    @Override
    public int membernickcheck(String membernickcheck) {
        return membersZMapper.membernickcheck(membernickcheck);
    }

    @Override
    public String memberlogin(String login_id) {
        return membersZMapper.memberlogin(login_id);
    }

    @Override
    public MemberVO memberinf(String login_id) {
        return membersZMapper.memberinf(login_id);
    }
}
