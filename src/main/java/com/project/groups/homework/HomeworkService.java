package com.project.groups.homework;

import com.project.groups.command.*;
import com.project.groups.util.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HomeworkService {

    int homeworkregForm(HomeWorkVO homeworkvo);

    HomeWorkVO homeworkvoselect(Integer homework_no);

    List<ExVO> listexvoselect(Integer homework_no);

    List<TestVO> listtestvoselect(Integer homework_no);

    List<HomeWorkVO> gethomeworklist(String login_id, Criteria cri);
    List<HomeWorkVO> getmyhomeworklist(String login_id, Criteria cri);
    int gethomeworktotal (String login_id, Criteria cri);
    int getmyhomeworktotal (String login_id, Criteria cri);

    List<GroupVO> getstdhome (GroupVO vo);

    List<GroupVO> getgroupnames(String login_id);

    void sendhomework(List<String> ids, Integer homework_no, Date homework_enddate);

    void updatesubmit(HomeWorkVO vo);
    void homeworkrecord(HomeWorkVO vo);

    int getscore(HomeWorkVO vo);

    void updatepoint(HomeWorkVO vo);

    List<HomeWorkVO> getrecord(Integer homework_no);

    List<HomeWorkVO>getstdhomeck(HomeWorkVO vo);

    List<CategoryVO> getcategory(String login_id);
    int regcategory(CategoryVO vo);
    int deletecategory(CategoryVO vo);
    int solutionp(String login_id);

    List<HomeWorkVO> notsolutionp(String login_id);

    List<HomeWorkVO> myrecord(String login_id, Criteria cri);
    int myrecordtotal(String login_id, Criteria cri);

    HomeWorkVO gethwinfo(HomeWorkVO vo);
    int hwidel(HomeWorkVO vo);

    int homeworkup(HomeWorkVO vo);
}
