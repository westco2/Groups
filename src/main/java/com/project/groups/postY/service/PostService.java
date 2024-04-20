package com.project.groups.postY.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.groups.domaindto.PostDetailResponseDto;
import com.project.groups.domaindto.PostRequestDto;
import com.project.groups.domaindto.PostSimpleResponseDto;
import com.project.groups.domainpost.Post;
import com.project.groups.domainpost.PostRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post).getId();
    }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);
    }

    @Transactional(readOnly=true)
    public List<PostSimpleResponseDto> findAll(){
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostSimpleResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly=true)
    public PostDetailResponseDto findById(Long id){
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new PostDetailResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다. id = " + id));
        post.update(requestDto);
        return id;
    }
}
