package com.project.groups.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.groups.postY.service.PostService;
import com.project.groups.domaindto.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor  // RequiredArgsConstructor 어노테이션을 사용하기 위한 임포트 추가
public class PostRestController {
    private final PostService postService;



    @PostMapping("/api/posts")
    public Long registerPost(@RequestBody PostRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/api/posts/{id}")
    public PostDetailResponseDto getPost(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }
}
