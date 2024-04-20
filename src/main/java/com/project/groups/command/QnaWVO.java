package com.project.groups.command;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaWVO {
	private String login_id; //아이디 pk
	private String korn_flnm; //이름
	private String group_nm;
	private String ccontent;
    private QnaVO list ;
}