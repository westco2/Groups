package com.project.groups.qnaW.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groups.command.GroupVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

@Service(value = "QnaWService")
public class QnaWServiceImpl implements QnaWService{
	
	@Autowired
	private QnaWMapper qnaWMapper;
	
	@Override
	public List<QnaVO> getList(Criteria cri, String login_id) {
		return qnaWMapper.getList(cri, login_id);
	}
	
	@Override
	public List<QnaVO> getList2(Criteria cri, String login_id ) {
		return qnaWMapper.getList2(cri, login_id);
	}
	
	@Override	
	public int regist(QnaVO vo) {
		return qnaWMapper.regist(vo);
	}
	
	@Override
	public QnaVO getDetail(int qnumber) {
		return qnaWMapper.getDetail(qnumber);
	}
	
	@Override
	public int update(QnaVO vo) {
		return qnaWMapper.update(vo);
	}
	
	@Override
	public void delete(int qnumber) {
		qnaWMapper.delete(qnumber);
	}
	
	@Override
	public int reply(QnaVO vo) {
		return qnaWMapper.reply(vo);
	}
	
	@Override
	public int getTotal() {
		return qnaWMapper.getTotal();
	}
	
	@Override
	public GroupVO getgroupinfo(String login_id) {
		return qnaWMapper.getgroupinfo(login_id);
	}
}