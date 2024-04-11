package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {

    int groupregForm(GroupVO vo);

    List<GroupVO> getgrouplist(String login_id);

    List<GroupVO> getstdInfo(GroupVO vo);

    void groupstupdate(GroupVO vo);
    void groupstbupdate(GroupVO vo);

    void groupdelete(GroupVO vo);

    List<GroupVO> getallgrouplist();

    int groupjoin(GroupVO vo);

    void groupjoinap (GroupVO vo);

    GroupVO getgroupdetail(Integer group_no);

    List<GroupVO> getgroupstdinfo(@Param("cri") Criteria cri, @Param("group_no") Integer group_no);

    int getgroupstdTotal(@Param("cri") Criteria cri, @Param("group_no") Integer group_no);

    void groupstddel(@Param("login_id") String login_id, @Param("group_no") Integer group_no);
}
