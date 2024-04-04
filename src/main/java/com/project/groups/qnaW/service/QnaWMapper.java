package com.project.groups.qnaW.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

@Mapper
public interface QnaWMapper {

	public int regist(QnaWVO vo);
	public int reply(QnaWVO vo);
	public ArrayList<QnaWVO> getList(Criteria cri);
	public int getTotal();
}