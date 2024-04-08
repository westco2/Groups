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
        int result = homeworkMapper.homeworkregForm(homeworkvo);
        homeworkvo.getList_exvo().forEach(a -> homeworkMapper.regex(a, homeworkvo.getLogin_id()));
        homeworkvo.getList_testvo().forEach(b -> homeworkMapper.regtest(b, homeworkvo.getLogin_id()));
        return result;
    }

}
