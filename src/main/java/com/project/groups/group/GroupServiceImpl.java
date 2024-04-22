package com.project.groups.group;

import com.project.groups.command.DataVO;
import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
import com.project.groups.command.QueryVO;
import com.project.groups.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "groupService")
public class GroupServiceImpl implements GroupService{


    @Autowired
    private GroupMapper groupMapper;

    @Override
    public int groupregForm(GroupVO vo) {
        return groupMapper.groupregForm(vo);
    }

    @Override
    public List<GroupVO> getgrouplist(String login_id, Criteria cri) {
        return groupMapper.getgrouplist(login_id, cri);
    }

    @Override
    public int getgrouplisttotal(String login_id, Criteria cri) {
        return groupMapper.getgrouplisttotal(login_id, cri);
    }

    @Override
    public List<GroupVO> getstdInfo(GroupVO vo) {
        return groupMapper.getstdInfo(vo);
    }

    @Override
    public void groupstupdate(List<GroupVO> list) {

        list.forEach(a -> groupMapper.groupstupdate(a));
    }

    @Override
    public void groupstbupdate(List<GroupVO> list) {
        list.forEach(a -> groupMapper.groupstbupdate(a));
    }

    @Override
    public void groupdelete(List<GroupVO> list) {
        list.forEach(a ->groupMapper.groupdelete(a));
    }

    @Override
    public List<GroupVO> getallgrouplist(Criteria cri) {
        return groupMapper.getallgrouplist(cri);
    }

    @Override
    public int getalltotal(Criteria cri) {
        return groupMapper.getalltotal(cri);
    }

    @Override
    public int groupjoin(GroupVO vo) {
        return groupMapper.groupjoin(vo);
    }

    @Override
    public void groupjoinap(List<GroupVO> list) {
        list.forEach(a-> groupMapper.groupjoinap(a));
    }

    @Override
    public GroupVO getgroupdetail(Integer group_no) {
        return groupMapper.getgroupdetail(group_no);
    }

    @Override
    public List<GroupVO> getgroupstdinfo(Criteria cri ,Integer group_no) {
        return groupMapper.getgroupstdinfo(cri,group_no);
    }

    @Override
    public int getstdtotal(Criteria cri, Integer group_no) {
        return groupMapper.getgroupstdTotal(cri,group_no);
    }

    @Override
    public void groupstddel(List<String> login_ids, Integer group_no) {
        login_ids.forEach(a-> groupMapper.groupstddel(a,group_no));
    }

    @Override
    public GroupVO groupwait(String login_id) {

        return groupMapper.groupwait(login_id);
    }

    @Override
    public int waitdel(String login_id) {
        return groupMapper.waitdel(login_id);
    }

    @Override
    public int aprv_yn(String login_id) {
        return groupMapper.aprv_yn(login_id);
    }

    @Override
    public int aprv_null(String login_id) {
        return groupMapper.aprv_null(login_id);
    }

    @Override
    public int getmygroupno(String login_id) {
        return groupMapper.getmygroupno(login_id);
    }

    @Override
    public List<QueryVO> getdashboardt(Integer group_no, String login_id) {
        return groupMapper.getdashboardt(group_no, login_id);
    }

    @Override
    public int getdatainfototal(Integer group_no, Criteria cri) {
        return groupMapper.getdatainfototal(group_no,cri);
    }

    @Override
    public List<DataVO> getdatainfo(Integer group_no, Criteria cri) {
        return groupMapper.getdatainfo(group_no, cri);
    }

    @Override
    public int groupcount(String login_id) {
        return groupMapper.groupcount(login_id);
    }

    @Override
    public MemberVO ckyou(String login_id) {
        return groupMapper.ckyou(login_id);
    }

    @Override
    public void youupdate(MemberVO vo) {
        groupMapper.youupdate(vo);
    }

    @Override
    public MemberVO myteacheryou(String login_id) {
        return groupMapper.myteacheryou(login_id);
    }


}
