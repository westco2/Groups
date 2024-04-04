package com.project.groups.qnaW.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

@Service(value = "QnaWService")
public class QnaWServiceImpl implements QnaWService{
	
	@Autowired
	private QnaWMapper qnaWMapper;
	
	@Override
	public ArrayList<QnaWVO> getList(Criteria cri) {
		return qnaWMapper.getList(cri);
	}
	
	@Override
	public int getTotal() {
		return qnaWMapper.getTotal();
	}
	
	@Override	
	public int regist(QnaWVO vo) {
		return qnaWMapper.regist(vo);
	}
	
	@Override
	public int reply(QnaWVO vo) {
		return qnaWMapper.reply(vo);
	}
}
