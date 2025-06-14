package com.example.ch3examples.mapper;

import com.example.ch3examples.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    List<Post> findAll();
    Post findById(Long id);
    int update(Post post);
    int delete(Long id);
}
