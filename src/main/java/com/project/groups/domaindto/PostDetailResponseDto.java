package com.project.groups.domaindto;

import java.time.LocalDateTime;

import com.project.groups.domainpost.Post;

import lombok.Getter;

@Getter
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String content;

    private String writer;

    public PostDetailResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();

        this.writer = post.getWriter();
    }
}
