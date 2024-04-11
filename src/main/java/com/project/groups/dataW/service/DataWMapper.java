package com.project.groups.dataW.service;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.project.groups.command.DataVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;
@Mapper
public interface DataWMapper {
	public ArrayList<DataVO> getList(Criteria cri);
	public int regist(DataVO vo);
	public DataVO getDetail(int GNUMBER);
	public int update(DataVO vo);
	public void delete(int GNUMBER);
	public int getTotal();
	
	
}