package com.project.groups.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaVO {
	
	//QNABOARD
	private Integer qnumber; //글번호 pk
	private String login_id; //MEMBERS FK 
	private Integer group_no; //fk
	private String qtitle; //글제목
	private String qcontent; //글내용
	private Timestamp qregdate; //등록일자
	private Timestamp qupddate; //수정일자
	private String qviews; //조회수
	private String qatta; //첨부파일명
	private String qattaurl; //첨부파일경로
	private String tname; //선생 이름
	private String korn_flnm;//학생이름
	private String group_nm; //그룹이름
	
	//COMMENT
	private Integer cnumber; //답변번호
	private String ccontent; //답변내용
	private Timestamp cregdate; //등록일자
	
}