package com.project.groups.homework;

import com.project.groups.command.*;
import com.project.groups.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<HomeWorkVO> gethomeworklist(String login_id,Criteria cri) {

        return homeworkMapper.gethomeworklist(login_id, cri);
    }

    @Override
    public List<HomeWorkVO> getmyhomeworklist(String login_id, Criteria cri) {
        return homeworkMapper.getmyhomeworklist(login_id, cri);
    }

    @Override
    public int gethomeworktotal(String login_id, Criteria cri) {
        return homeworkMapper.gethomeworktotal(login_id, cri);
    }

    @Override
    public int getmyhomeworktotal(String login_id, Criteria cri) {
        return homeworkMapper.getmyhomeworktotal(login_id, cri);
    }

    @Override
    public List<GroupVO> getstdhome(GroupVO vo) {

        return homeworkMapper.getstdhome(vo);
    }

    @Override
    public List<GroupVO> getgroupnames(String login_id) {
        return homeworkMapper.getgroupnames(login_id);
    }

    @Override
    public void sendhomework(List<String> ids, Integer homework_no, Date homework_enddate) {
        ids.forEach(a -> homeworkMapper.sendhomework(a,homework_no,homework_enddate));
    }

    @Override
    public void updatesubmit(HomeWorkVO vo) {
        homeworkMapper.submitupdate(vo);
    }

    @Override
    public void homeworkrecord(HomeWorkVO vo) {
        homeworkMapper.recordhomework(vo);
    }

    @Override
    public int getscore(HomeWorkVO vo) {
        return homeworkMapper.getscore(vo);
    }

    @Override
    public void updatepoint(HomeWorkVO vo) {
        homeworkMapper.updatepoint(vo);
    }

    @Override
    public List<HomeWorkVO> getrecord(Integer homework_no) {
        return homeworkMapper.getrecord(homework_no);
    }

    @Override
    public List<HomeWorkVO> getstdhomeck(HomeWorkVO vo) {
        return homeworkMapper.stdhomeworkinfo(vo);
    }

    @Override
    public List<CategoryVO> getcategory(String login_id) {
        return homeworkMapper.getcategory(login_id);
    }

    @Override
    public int regcategory(CategoryVO vo) {
        return homeworkMapper.regcategory(vo);
    }

    @Override
    public int deletecategory(CategoryVO vo) {

        return homeworkMapper.deletecategory(vo);
    }

    @Override
    public int solutionp(String login_id) {
        return homeworkMapper.solutionp(login_id);
    }

    @Override
    public List<HomeWorkVO> notsolutionp(String login_id) {
        return homeworkMapper.notsolutionp(login_id);
    }

    @Override
    public List<HomeWorkVO> myrecord(String login_id, Criteria cri) {
        return homeworkMapper.myrecord(login_id, cri);
    }

    @Override
    public int myrecordtotal(String login_id, Criteria cri) {
        return homeworkMapper.myrecordtotal(login_id, cri);
    }

    @Override
    public HomeWorkVO gethwinfo(HomeWorkVO vo) {
        return homeworkMapper.hwinfo(vo);
    }

    @Override
    public int hwidel(HomeWorkVO vo) {
        return homeworkMapper.hwdel(vo);
    }

    @Override
    public int homeworkup(HomeWorkVO vo) {
        return homeworkMapper.homeworkup(vo);
    }

    @Override
    public int qcnt(String login_id) {
        return homeworkMapper.qcnt(login_id);
    }

}
