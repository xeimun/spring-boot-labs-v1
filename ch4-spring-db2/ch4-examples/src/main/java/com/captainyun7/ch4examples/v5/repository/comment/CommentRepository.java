package com.captainyun7.ch4examples.v5.repository.comment;

import com.captainyun7.ch4examples.v5.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}
