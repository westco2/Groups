package com.project.groups.dataW.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.project.groups.command.DataVO;
import com.project.groups.command.GroupVO;
import com.project.groups.command.HomeWorkVO;
import com.project.groups.command.UploadVO;
import com.project.groups.util.Criteria;
public interface DataWService {
	public ArrayList<DataVO> getList(String login_id,Criteria cri);
	public ArrayList<DataVO> getList2(String login_id,Criteria cri);
	public int regist(DataVO vo, List<UploadVO> list);
	public DataVO getDetail(Integer gnumber);
	public int update(DataVO vo);
	public void delete(Integer gnumber);
	public void deletefileupload(Integer gnumber);
	public void deleteFile(String filename);
	public int getTotal(String login_id, Criteria cri);
	public List<GroupVO> getgroupname(String login_id);
	public List<UploadVO> getfile(Integer gnumber);
}