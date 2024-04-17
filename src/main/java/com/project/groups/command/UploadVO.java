package com.project.groups.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadVO {
	private Integer upload_no; //파일번호
	private String	filename; //실제첨부파일명
	private String  filepath; //업로드 될 날짜폴더
	private String  fileurl; //첨부파일경로
	private String  uuid; //랜덤값
	private Timestamp fregdate; //등록일자
	
	//MEMBERS 테이블 값이지만 조회편의성 높이기위해 VO에 추가
	private String login_id; //fk 
	private Integer gnumber; //fk
}