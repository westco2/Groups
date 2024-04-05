package com.project.groups.group;

import com.project.groups.command.GroupVO;
import com.project.groups.command.MemberVO;
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
    public List<GroupVO> getgrouplist() {
        return groupMapper.getgrouplist();
    }

    @Override
    public List<MemberVO> getstdInfo(GroupVO vo) {
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
    public void groupjoin(GroupVO vo) {
        groupMapper.groupjoin(vo);
    }


}
