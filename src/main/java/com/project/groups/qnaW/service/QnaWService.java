package com.project.groups.qnaW.service;
import java.util.List;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
public interface QnaWService {
	public List<QnaWVO> getList(Criteria cri );
	public List<QnaVO> getList2(Criteria cri);
	public int regist(QnaVO vo);
	public QnaVO getDetail(int QNUMBER);
	public int update(QnaVO vo);
	public void delete(int QNUMBER);

	public int reply(QnaVO vo);
	public int getTotal();
}
