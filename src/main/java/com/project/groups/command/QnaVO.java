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
public class QnaVO {
	
	//QNABOARD
	private Integer QNUMBER; //글번호 pk
	private String LOGIN_ID; //MEMBERS FK 
	private Integer GROUP_NO; //fk
	private String QTITLE; //글제목
	private String QCONTENT; //글내용
	private Timestamp QREGDATE; //등록일자
	private Timestamp QUPDDATE; //수정일자
	private String QVIEWS; //조회수
	private String QATTA; //첨부파일명
	private String QATTAURL; //첨부파일경로
	
	//COMMENT
	private Integer CNUMBER; //답변번호
	private String CCONTENT; //답변내용
	private Timestamp CREGDATE; //등록일자
	private Timestamp CUPDDATE; //수정일자
}