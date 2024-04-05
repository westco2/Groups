package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {

    int groupregForm(GroupVO vo);

    List<GroupVO> getgrouplist();

    List<MemberVO> getstdInfo(GroupVO vo);

    void groupstupdate(List<GroupVO> list);
    void groupstbupdate(List<GroupVO> list);
    void groupdelete(List<GroupVO> list);

    List<GroupVO> getallgrouplist();

    void groupjoin(GroupVO vo);
}
