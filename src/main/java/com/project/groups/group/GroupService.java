package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.util.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {

    int groupregForm(GroupVO vo);

    List<GroupVO> getgrouplist(String login_id);

    List<GroupVO> getstdInfo(GroupVO vo);

    void groupstupdate(List<GroupVO> list);
    void groupstbupdate(List<GroupVO> list);
    void groupdelete(List<GroupVO> list);

    List<GroupVO> getallgrouplist();

    int groupjoin(GroupVO vo);

    void groupjoinap(List<GroupVO> list);

    GroupVO getgroupdetail(Integer group_no);

    List<GroupVO> getgroupstdinfo(Criteria cri ,Integer group_no);

    int getstdtotal(Criteria cri,Integer group_no);

    void groupstddel(List<String> login_ids, Integer group_no);
}
