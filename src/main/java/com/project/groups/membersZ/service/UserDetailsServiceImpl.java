package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MembersZMapper membersZMapper;

    @Autowired
    public UserDetailsServiceImpl(MembersZMapper membersZMapper){
        this.membersZMapper = membersZMapper;
    }
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = membersZMapper.memberinf(username);
        if(memberVO == null){
            throw new UsernameNotFoundException("아이디가 없습니다"+ username);
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(memberVO.getRole()));
        CustomUserDetails userDetails = new CustomUserDetails(
                memberVO.getLogin_id(),
                memberVO.getPswd(),
                authorities
        );
        System.out.println("memberVO = " + memberVO);
        System.out.println("userDetails = " + userDetails);
        System.out.println(userDetails.getAuthorities());
        userDetails.setMemberVO(memberVO);

        System.out.println("userDetails = " + userDetails);

        return userDetails;
    }

}
