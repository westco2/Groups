package com.project.groups.homework;

import com.project.groups.command.ExVO;
import com.project.groups.command.HomeWorkVO;
import com.project.groups.command.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public HomeWorkVO homeworkvoselect(Integer homework_no){
        return homeworkMapper.homeworkvoselect(homework_no);
    }

    @Override
    public List<ExVO> listexvoselect(Integer homework_no){
        return homeworkMapper.listexvoselect(homework_no);
    }
    @Override
    public List<TestVO> listtestvoselect(Integer homework_no){
        return homeworkMapper.listtestvoselect(homework_no);
    }
}
