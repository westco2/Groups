package com.project.groups.group;

import com.project.groups.command.DataVO;
import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.command.QueryVO;
import com.project.groups.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {

    int groupregForm(GroupVO vo);

    List<GroupVO> getgrouplist(@Param("login_id") String login_id, @Param("cri") Criteria cri);
    int getgrouplisttotal(@Param("login_id") String login_id, @Param("cri") Criteria cri);

    List<GroupVO> getstdInfo(GroupVO vo);

    void groupstupdate(GroupVO vo);
    void groupstbupdate(GroupVO vo);

    void groupdelete(GroupVO vo);

    List<GroupVO> getallgrouplist(@Param("cri") Criteria cri);
    int getalltotal(@Param("cri") Criteria cri);

    int groupjoin(GroupVO vo);

    void groupjoinap (GroupVO vo);

    GroupVO getgroupdetail(Integer group_no);

    List<GroupVO> getgroupstdinfo(@Param("cri") Criteria cri, @Param("group_no") Integer group_no);

    int getgroupstdTotal(@Param("cri") Criteria cri, @Param("group_no") Integer group_no);

    void groupstddel(@Param("login_id") String login_id, @Param("group_no") Integer group_no);

    GroupVO groupwait (String login_id);

    int waitdel(String login_id);

    int aprv_yn(String login_id);
    int aprv_null(String login_id);

    int getmygroupno(String login_id);

    List<QueryVO> getdashboardt(@Param("group_no") Integer group_no, @Param("login_id")String login_id);

    int getdatainfototal(@Param("group_no") Integer group_no, @Param("cri")Criteria cri);
    List<DataVO> getdatainfo(@Param("group_no") Integer group_no, @Param("cri")Criteria cri);
}
