package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO { //회원 테이블 정보
    private String role; //회원권한 회원가입 후 관리자가 부여함
    private String login_id; //아이디
    private String pswd; //비밀번호
    private String korn_flnm; //이름
    private String idfr_telno; //연락처
    private String date_birth; //생년월일
    private String adm_eml_addr; //이메일
    private String lotno_daddr; //주소
    private LocalDateTime date_subscription; //가입일자 처음엔 null로 받고 DB에 넣을 때
    private LocalDateTime date_del; //탈퇴일자 처음엔 null로 받고 DB에 넣을 때
    private String user_nickname; //닉네임
    private Integer score; //학생 점수
    private String tier; //선생님결제 티어
    private MultipartFile file;
    private String youtube_id;
    private String filename;
    private String upload_dir;
}
