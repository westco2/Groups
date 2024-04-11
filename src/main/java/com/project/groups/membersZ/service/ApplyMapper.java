package com.project.groups.membersZ.service;

import com.project.groups.command.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyMapper {

    List<MemberVO> applymemberlist();

    void approveteacher(String loginId);
}
