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
	private String LOGIN_ID; //아이디 pk
	private String KORN_FLNM; //이름
	
	private String GROUP_NM;
	
	private String CCONTENT;
	
	
//    private String ROLE; //회원권한 회원가입 후 관리자가 UserDetails 로 부여함
//    private String PSWD; //비밀번호
//    private String IDFR_TELNO; //연락처
//    private String DATE_BIRTH; //생년월일
//    private String ADM_EML_ADDR; //이메일
//    private String LOTNO_DADDR; //주소
//    private Timestamp DATE_SUBSCRIPTION; //가입일자 처음엔 null로 받고 DB에 넣을 때
//    private Timestamp DATE_DEL; //탈퇴일자 처음엔 null로 받고 DB에 넣을 때
//    private String USER_NICKNAME; //닉네임
    
    private QnaVO list ;
    
}