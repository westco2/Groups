package com.project.groups.qnaW.service;
import java.util.List;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
public interface QnaWService {
	public List<QnaWVO> getList(Criteria cri);
	public int regist(QnaVO vo);
	public int reply(QnaVO vo);
	public Integer getTotal();
}