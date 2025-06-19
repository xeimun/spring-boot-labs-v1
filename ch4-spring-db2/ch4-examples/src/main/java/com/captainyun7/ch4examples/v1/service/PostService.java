package com.captainyun7.ch4examples.v1.service;

import com.captainyun7.ch4examples.v1.domain.Post;
import com.captainyun7.ch4examples.v1.dto.*;
import com.captainyun7.ch4examples.v1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository repository;

    public PostResponse createPost(PostCreateRequest request) {
        Post post = request.toDomain();
        Post saved = repository.save(post);
        return PostResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return repository.findAll().stream()
                .map(PostResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        return repository.findById(id)
                .map(PostResponse::from)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

    public PostResponse updatePost(Long id, PostUpdateRequest request) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return PostResponse.from(post);
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }

}