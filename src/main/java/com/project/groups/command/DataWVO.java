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
public class DataWVO {
	//GROUPFILE
	private Integer gnumber; //자료번호
	private Integer user_no; //회원번호 //MEMBERS FK
	private String gname; //강의제목
	private String gfilename; //학습자료명
	private String gfilecon; //학습자료내용
	private String gfiletype; //학습자료유형
	private String gfileurl; //학습자료주소
	private String gatta; //첨부파일명
	private String gattaurl; //첨부파일경로
	private Timestamp gregdate; //등록일자
}