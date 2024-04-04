package com.project.groups.qnaW.service;

import java.util.ArrayList;

import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

public interface QnaWService {

	public ArrayList<QnaWVO> getList(Criteria cri);
	public int regist(QnaWVO vo);
	public int reply(QnaWVO vo);
	public int getTotal();
}