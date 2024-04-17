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
	public int regist(DataVO vo, List<MultipartFile> list) {
		int result = dataWMapper.regist(vo);
		
		 list.forEach(file -> {
	            //1.파일명 = file.getOriginalfilename();
	            //브라우저별로 파일의 경로가 포함되서 올라오는 경우가 있기때문에
	            System.out.println(file.getOriginalFilename());
	            String filename= file.getOriginalFilename(); //원본파일명 DB에 저장
	            filename = filename.substring( filename.lastIndexOf("\\") + 1 );
	            //2. 파일 사이즈
	            //System.out.println(file.getSize());
	            //3. 동일한 파일로 업로드가 되면, 기존파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일명칭 바꿈
	            String uuid = UUID.randomUUID().toString(); //db저장
	            //날짜별로 폴더생성
	            String filePath = makeFolder(); //yyyyMMdd db저장
	            //3. 업로드할 경로
	            String savePath = uploadPath + "/" + filePath + "/" + uuid + "_" + filename;

	            try{
	                File saveFile = new File(savePath);
	                file.transferTo(saveFile);

	            }catch (Exception e) {
	                e.printStackTrace();
	            }
	            //업로드 이후에는 DB에 경로를 저장
	            //prod_id는 서비스영역에서 구할수 있는방법이 없음
	            dataWMapper.upload(UploadVO.builder().fileurl(savePath).filename(filename).filepath(filePath).uuid(uuid).login_id(vo.getLogin_id()).build());
	        });

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
	public GroupVO getgroupname(String login_id) {
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