package com.captainyun7.ch4examples.v4.repository;

import com.captainyun7.ch4examples.v4.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 제목에 검색어 포함
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);
    // 작성자와 검색어 일치
    Page<Post> findByAuthor(String author, Pageable pageable);
    // 본문 내용 포함 + 작성자 일치
    Page<Post> findByAuthorAndTitleContaining(String author, String title, Pageable pageable);
    // 작성일자 이후 또는 이전 (createdAt >= ?)
    Page<Post> findByCreatedAtAfter(LocalDateTime createdAt, Pageable pageable);

}
