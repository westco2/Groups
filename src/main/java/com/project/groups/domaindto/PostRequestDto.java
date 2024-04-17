package com.project.groups.domaindto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 추가
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String writer;
}