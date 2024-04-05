package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    int groupregForm(GroupVO vo);

    List<GroupVO> getgrouplist();

    List<MemberVO> getstdInfo(GroupVO vo);

    void groupstupdate(GroupVO vo);
    void groupstbupdate(GroupVO vo);

    void groupdelete(GroupVO vo);

    List<GroupVO> getallgrouplist();

    void groupjoin(GroupVO vo);
}
