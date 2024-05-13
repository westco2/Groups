package com.project.groups.homework;

import com.project.groups.command.*;
import com.project.groups.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface HomeworkMapper {

    int homeworkregForm(HomeWorkVO homeworkvo);
    void regex(@Param("exvo") ExVO exvo, @Param("login_id") String login_id);
    void regtest(@Param("testvo") TestVO testvo, @Param("login_id") String login_id);

    HomeWorkVO homeworkvoselect(Integer homework_no);

    List<ExVO> listexvoselect(Integer homework_no);

    List<TestVO> listtestvoselect(Integer homework_no);

    List<HomeWorkVO> gethomeworklist(@Param("login_id") String login_id, @Param("cri")Criteria cri);
    List<HomeWorkVO> getmyhomeworklist(@Param("login_id") String login_id, @Param("cri")Criteria cri);
    int gethomeworktotal (@Param("login_id") String login_id, @Param("cri")Criteria cri);
    int getmyhomeworktotal (@Param("login_id") String login_id, @Param("cri")Criteria cri);

    void sendhomework (@Param("login_id")String login_id, @Param("homework_no")Integer homework_no, @Param("homework_enddate")Date homework_enddate);

    List<GroupVO> getstdhome(GroupVO vo);

    List<GroupVO> getgroupnames(String login_id);

    void submitupdate(HomeWorkVO vo);

    void recordhomework(HomeWorkVO vo);

    int getscore(HomeWorkVO vo);

    void updatepoint(HomeWorkVO vo);
    List<HomeWorkVO> getrecord(Integer homework_no);


    List<HomeWorkVO>stdhomeworkinfo(HomeWorkVO vo);

    int regcategory(CategoryVO vo);
    List<CategoryVO> getcategory(String login_id);
    int deletecategory(CategoryVO vo);

    int solutionp(String login_id);

    List<HomeWorkVO> notsolutionp(String login_id);

    List<HomeWorkVO> myrecord(@Param("login_id") String login_id, @Param("cri")Criteria cri);
    int myrecordtotal(@Param("login_id") String login_id, @Param("cri")Criteria cri);

    HomeWorkVO hwinfo(HomeWorkVO vo);
    int hwdel(HomeWorkVO vo);

    int homeworkup(HomeWorkVO vo);
    int qcnt(String login_id);
}
