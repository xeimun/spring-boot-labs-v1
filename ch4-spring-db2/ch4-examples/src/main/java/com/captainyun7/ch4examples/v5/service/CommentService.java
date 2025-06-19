package com.captainyun7.ch4examples.v5.service;

import com.captainyun7.ch4examples.v5.domain.Comment;
import com.captainyun7.ch4examples.v5.domain.Post;
import com.captainyun7.ch4examples.v5.dto.comment.CommentCreateRequest;
import com.captainyun7.ch4examples.v5.dto.comment.CommentPageResponse;
import com.captainyun7.ch4examples.v5.dto.comment.CommentResponse;
import com.captainyun7.ch4examples.v5.dto.comment.CommentUpdateRequest;
import com.captainyun7.ch4examples.v5.repository.comment.CommentRepository;
import com.captainyun7.ch4examples.v5.repository.post.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponse createComment(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다"));

        Comment parent = null;
        if (request.getParentId() != null) {
            parent = commentRepository.findById(request.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다"));
        }

        Comment comment = Comment.builder()
                .post(post)
                .parent(parent)
                .author(request.getAuthor())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);
    }

    public CommentResponse createComment2(Long postId, CommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다. ID: " + postId));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(request.getAuthor())
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public CommentPageResponse getCommentsByPost(Long postId, Pageable pageable) {
        Page<CommentResponse> page = commentRepository
                .findByPostIdOrderByCreatedAtAsc(postId, pageable)
                .map(CommentResponse::from);

        return CommentPageResponse.from(page);
    }

    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다." + commentId));

        comment.setContent(request.getContent());

        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }
}
