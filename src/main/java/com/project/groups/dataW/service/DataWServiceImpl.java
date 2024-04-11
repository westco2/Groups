package com.project.groups.dataW.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groups.command.DataVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.util.Criteria;

@Service(value = "DataWService")
public class DataWServiceImpl implements DataWService{
	
	@Autowired
	private DataWMapper dataWMapper;
	
	@Override
	public ArrayList<DataVO> getList(Criteria cri) {
		return dataWMapper.getList(cri);
	}
	
	@Override
	public int regist(DataVO vo) {
		return dataWMapper.regist(vo);
	}
	
	@Override
	public DataVO getDetail(int GNUMBER) {
		return dataWMapper.getDetail(GNUMBER);
	}
	
	@Override
	public int update(DataVO vo) {
		return dataWMapper.update(vo);
	}
	
	@Override
	public void delete(int GNUMBER) {
		dataWMapper.delete(GNUMBER);
	}
	
	@Override
	public int getTotal() {
		return dataWMapper.getTotal();
	}
	
}