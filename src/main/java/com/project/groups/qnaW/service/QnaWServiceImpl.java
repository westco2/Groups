package com.project.groups.qnaW.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

@Service(value = "QnaWService")
public class QnaWServiceImpl implements QnaWService{
	
	@Autowired
	private QnaWMapper qnaWMapper;
	
	@Override
	public List<QnaWVO> getList(Criteria cri) {
		return qnaWMapper.getList(cri);
	}
	
	@Override
	public Integer getTotal() {
		return qnaWMapper.getTotal();
	}
	
	@Override	
	public int regist(QnaVO vo) {
		return qnaWMapper.regist(vo);
	}
	
	@Override
	public int reply(QnaVO vo) {
		return qnaWMapper.reply(vo);
	}
}
