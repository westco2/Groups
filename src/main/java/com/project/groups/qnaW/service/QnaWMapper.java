package com.project.groups.qnaW.service;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
@Mapper
public interface QnaWMapper {
	public List<QnaWVO> getList(Criteria cri);
	public int regist(QnaVO vo);
	public int reply(QnaVO vo);
	public Integer getTotal();
}