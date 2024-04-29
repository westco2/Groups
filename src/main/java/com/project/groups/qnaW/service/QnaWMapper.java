package com.project.groups.qnaW.service;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.project.groups.command.GroupVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
@Mapper
public interface QnaWMapper {
	public List<QnaVO> getList(@Param("cri")Criteria cri, @Param("login_id") String login_id);
	public List<QnaVO> getList2(@Param("cri")Criteria cri, @Param("login_id") String login_id);
	public int regist(QnaVO vo);
	public QnaVO getDetail(int qnumber);
	public int update(QnaVO vo);
	public void delete(int qnumber);
	public int reply(QnaVO vo);
	public int getTotal(@Param("login_id") String login_id, @Param("cri") Criteria cri);
	public int getTotalT(@Param("login_id") String login_id, @Param("cri") Criteria cri);
	public GroupVO getgroupinfo (String login_id);
}