package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
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
    public List<GroupVO> getgrouplist(String login_id) {
        return groupMapper.getgrouplist(login_id);
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
    public List<GroupVO> getallgrouplist() {
        return groupMapper.getallgrouplist();
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


}
