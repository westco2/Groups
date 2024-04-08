package com.project.groups.homework;

import com.project.groups.command.ExVO;
import com.project.groups.command.HomeWorkVO;
import com.project.groups.command.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    int homeworkregForm(HomeWorkVO homeworkvo);
    void regex(@Param("exvo") ExVO exvo, @Param("login_id") String login_id);
    void regtest(@Param("testvo") TestVO testvo, @Param("login_id") String login_id);

    HomeWorkVO homeworkvoselect(Integer homework_no);

    List<ExVO> listexvoselect(Integer homework_no);

    List<TestVO> listtestvoselect(Integer homework_no);
}
