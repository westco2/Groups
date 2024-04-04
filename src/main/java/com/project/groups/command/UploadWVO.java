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
public class UploadWVO {
	private Integer UPLOAD_NO; //자료번호
	private String	FILENAME; //실제첨부파일명
	private String  FILEPATH; //업로드 될 날짜폴더
	private String  FILEURL; //첨부파일경로
	private String  UUID; //랜덤값
	private Timestamp FREGDATE; //등록일
	
	//MEMBERS 테이블 값이지만 조회편의성 높이기위해 VO에 추가
	private String USER_NO; //fk(writer) //작성자
	private Integer GNUMBER; //fk
}