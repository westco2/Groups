package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MembersZMapper membersZMapper;

    @Autowired
    public UserDetailsServiceImpl(MembersZMapper membersZMapper){
        this.membersZMapper = membersZMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = membersZMapper.memberinf(username);
        if(memberVO == null){
            throw new UsernameNotFoundException("아이디가 없습니다"+ username);
        }
        System.out.println("memberVO = " + memberVO);
        return new org.springframework.security.core.userdetails.User(
                memberVO.getLogin_id(), memberVO.getPswd(), Collections.emptyList());
    }

}
