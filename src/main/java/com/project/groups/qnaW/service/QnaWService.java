package com.project.groups.qnaW.service;
import java.util.List;
import com.project.groups.command.GroupVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
public interface QnaWService {
	public List<QnaVO> getList(Criteria cri, String login_id );
	public List<QnaVO> getList2(Criteria cri, String login_id);
	public int regist(QnaVO vo);
	public QnaVO getDetail(int qnumber);
	public int update(QnaVO vo);
	public void delete(int qnumber);
	public int reply(QnaVO vo);
	public int getTotal(String login_id, Criteria cri);
	public GroupVO getgroupinfo(String login_id);
}