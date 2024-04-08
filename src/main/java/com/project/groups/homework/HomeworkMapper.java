package com.project.groups.homework;

import com.project.groups.command.HomeWorkVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkMapper {

    int homeworkregForm(HomeWorkVO homeworkvo);
}
