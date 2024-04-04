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
public class QnaWVO {
	
	//QNABOARD
	private Integer qnumber; //글번호
	private Integer user_no; //회원번호 //MEMBERS FK
	private Integer group_no;
	private String qtitle; //글제목
	private String qcontent; //글내용
	private Timestamp qregdate; //등록일자
	private Timestamp qupddate; //수정일자
	private String qviews; //조회수
	private String qatta; //첨부파일명
	private String qattaurl; //첨부파일경로
	
	//COMMENT
	private Integer cnumber; //답변번호
	private String ccontent; //답변내용
	private Timestamp cregdate; //등록일자
	private Timestamp cupddate; //수정일자
}
