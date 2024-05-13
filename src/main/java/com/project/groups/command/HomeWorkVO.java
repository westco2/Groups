package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeWorkVO {
    private String homework_non; //암호화키사용
    private Integer homework_no;
    private String login_id;
    private String homework_level;
    private String homework_title;
    private String homework_content;
    private String homework_hint;
    private LocalDateTime homework_register;
    private LocalDateTime homework_modify;
    private String homework_language;
    private Integer homework_time;

    private Integer homwork_score;
    private List<ExVO> list_exvo;
    private List<TestVO> list_testvo;
    private String code;
    private Integer score; // 숙제 점수
    private Integer point;//학생 점수
    private LocalDateTime record_date; //이력 날짜

    private Integer record_score;
    private String record_code;

    private String category; // 숙제 유형

    private Integer average_score;// 숙제 평균점수
    private Integer percentage; //숙제 정답률

    private Date homework_enddate;
    private LocalDateTime homework_recieve; //
    private LocalDateTime submit_date;
    private String homework_st;
    private Integer homework_score;
    private String korn_flnm;
}
