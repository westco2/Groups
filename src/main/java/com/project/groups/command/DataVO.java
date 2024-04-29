package com.project.groups.command;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	private Integer gnumber; //자료번호
	private Integer gviews; //조회수
	private Integer group_no; //그룹번호																																																																																																																																																																		;
	private String login_id; //MEMBERS FK
	private String login_idid;
	private String gname; //강의제목
	private String gfilename; //학습자료명
	private String gfilecon; //학습자료내용
	private String gfileurl; //학습자료주소
	private LocalDateTime gregdate; //등록일자
	
	//GROUP_LIST
	private String group_nm;
	private String korn_flnm;
}