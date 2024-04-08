package com.project.groups.homework;

import com.project.groups.command.ExVO;
import com.project.groups.command.HomeWorkVO;
import com.project.groups.command.TestVO;

import java.util.List;

public interface HomeworkService {

    int homeworkregForm(HomeWorkVO homeworkvo);

    HomeWorkVO homeworkvoselect(Integer homework_no);

    List<ExVO> listexvoselect(Integer homework_no);

    List<TestVO> listtestvoselect(Integer homework_no);
}
