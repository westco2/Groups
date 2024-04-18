package com.project.groups.dataW.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.groups.command.DataVO;
import com.project.groups.command.GroupVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.command.UploadVO;
import com.project.groups.util.Criteria;

@Service(value = "DataWService")
public class DataWServiceImpl implements DataWService{
	
	@Autowired
	private DataWMapper dataWMapper;


	@Value("${project.upload.path}")
	private String uploadPath;
	
	public String makeFolder() {
		String filepath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		File file = new File(uploadPath + "/" + filepath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		return filepath;
	}
	
	@Override
	public ArrayList<DataVO> getList(String login_id ,Criteria cri) {
		return dataWMapper.getList(login_id ,cri);
	}
	
	@Override
	public int regist(DataVO vo, List<UploadVO> list) {
		int result = dataWMapper.regist(vo);
		list.forEach(a->dataWMapper.upload(a));


		return result;
		
	} 
	
	@Override
	public DataVO getDetail(Integer gnumber) {
		return dataWMapper.getDetail(gnumber);
	}
	
	@Override
	public int update(DataVO vo) {
		return dataWMapper.update(vo);
	}
	
	@Override
	public void delete(Integer gnumber) {
		dataWMapper.delete(gnumber);
	}
	
	@Override
	public int getTotal(String login_id, Criteria cri) {
		return dataWMapper.getTotal(login_id, cri);
	}
	
	
	@Override
	public List<GroupVO> getgroupname(String login_id) {
		return dataWMapper.getgroupname(login_id);
	}
	
	
	@Override
	public ArrayList<DataVO> getList2(String login_id, Criteria cri) {
		return dataWMapper.getList2(login_id, cri);
	}
	@Override
	public List<UploadVO> getfile(Integer gnumber) {
		return dataWMapper.getfile(gnumber);
	}
	
}