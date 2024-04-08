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
public class DataVO {
	//GROUPFILE
	private Integer GNUMBER; //자료번호
	private Integer GVIEWS; //조회수
	private String LOGIN_ID; //회원번호 //MEMBERS FK
	private String GNAME; //강의제목
	private String GFILENAME; //학습자료명
	private String GFILECON; //학습자료내용
	private String GFILERUL; //학습자료주소
	private Timestamp GREGDATE; //등록일자
}