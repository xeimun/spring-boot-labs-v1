package com.example.ch3examples.mapper;

import com.example.ch3examples.domain.Post;
import com.example.ch3examples.dto.PostSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
//    List<Post> findAll();
    List<Post> findAll(PostSearch search);
    int count(PostSearch search); // 전체 개수
    Post findById(Long id);
    int update(Post post);
    int delete(Long id);
}
