package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupVO {

    private Integer group_non;
    private Integer group_no;
    private Integer user_no;
    private String group_nm;
    private String korn_flnm;

    private Date group_start;

    private Date group_end;
    private Integer group_pp;
    private String group_st;
    private Integer student_count; //그룹 인원
    private Integer group_mt; //그룹 수업자료
    private Integer std_q; //그룹 질문 수
    
    private String sub_class; //구독등급

}
