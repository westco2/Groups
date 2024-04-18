package com.project.groups.dataW.service;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.project.groups.command.DataVO;
import com.project.groups.command.GroupVO;
import com.project.groups.command.QnaVO;
import com.project.groups.command.QnaWVO;
import com.project.groups.command.UploadVO;
import com.project.groups.util.Criteria;
@Mapper
public interface DataWMapper {
	public ArrayList<DataVO> getList(@Param("login_id") String login_id,@Param("cri")Criteria cri);
	public ArrayList<DataVO> getList2(@Param("login_id") String login_id,@Param("cri")Criteria cri);
	public int regist(DataVO vo);
	public DataVO getDetail(Integer gnumber);
	public int update(DataVO vo);
	public void delete(Integer gnumber);
	public int getTotal(@Param("login_id")String login_id ,@Param("cri")Criteria cri);
	public void upload(UploadVO uploadvo);
	public void registFile(UploadVO vo);
	public List<GroupVO> getgroupname(String login_id);
	public List<UploadVO> getfile(Integer gnumber);
}