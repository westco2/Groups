package com.project.groups.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeWorkVO {
    private Integer homework_no;
    private String login_id;
    private String homework_level;
    private String homework_title;
    private String homework_content;
    private String homework_hint;
    private Timestamp homework_register;
    private Timestamp homework_modify;
    private String homework_language;
    private Integer homework_time;

    private List<ExVO> list_exvo;
    private List<TestVO> list_testvo;
}
