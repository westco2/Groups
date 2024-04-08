package com.project.groups.homework;

import com.project.groups.command.HomeWorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "homeworkService")
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public int homeworkregForm(HomeWorkVO homeworkvo) {
        return homeworkMapper.homeworkregForm(homeworkvo);
    }
}
