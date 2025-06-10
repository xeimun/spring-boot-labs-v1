package com.captainyun7.ch2examples._02_rest_controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @RestController 예제
 * - @Controller + @ResponseBody의 조합
 * - 모든 메서드에 @ResponseBody가 적용됨 (뷰 대신 데이터 반환)
 */
@RestController
@RequestMapping("/rest")
public class BasicRestController {

    // 포스트 저장소 (간단한 인메모리 데이터베이스 역할)
    private final ConcurrentHashMap<Long, Post> postStore = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    // 초기 데이터 추가
    public BasicRestController() {
        Post post1 = new Post("첫번째 포스트", "안녕하세요, 첫번째 포스트입니다.");
        Post post2 = new Post("두번째 포스트", "반갑습니다, 두번째 포스트입니다.");
        Post post3 = new Post("세번째 포스트", "또 만나요, 세번째 포스트입니다.");
        
        post1.setId(counter.getAndIncrement());
        post2.setId(counter.getAndIncrement());
        post3.setId(counter.getAndIncrement());
        
        postStore.put(post1.getId(), post1);
        postStore.put(post2.getId(), post2);
        postStore.put(post3.getId(), post3);
    }

    /**
     * 문자열 데이터를 직접 반환
     */
    @GetMapping("/hello")
    public String hello() {
        return "안녕";
    }

    /**
     * 객체를 반환하면 자동으로 JSON으로 변환됨
     */
    @GetMapping("/post")
    public Post getPost() {
        Post post = new Post("샘플 포스트", "RestController 예제입니다.");
        post.setId(0L);
        return post;
    }

    /**
     * 컬렉션을 반환하면 JSON 배열로 변환됨
     */
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return new ArrayList<>(postStore.values());
    }

    /**
     * CREATE - 새 포스트 생성
     */
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        long id = counter.getAndIncrement();
        post.setId(id);
        postStore.put(id, post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    /**
     * READ - 특정 ID의 포스트 조회
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postStore.get(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * UPDATE - 특정 ID의 포스트 수정
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        if (postStore.containsKey(id)) {
            updatedPost.setId(id);
            postStore.put(id, updatedPost);
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE - 특정 ID의 포스트 삭제
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postStore.containsKey(id)) {
            postStore.remove(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 내부 정적 클래스 - 포스트 데이터 모델
     */
    //(Jackson 라이브러리가 JSON 변환 시 사용)
    @Getter
    @Setter
    @NoArgsConstructor         // 기본 생성자 추가 (JSON 직렬화를 위해 필요)
    public static class Post {
        private Long id;
        private String title;
        private String body;

        public Post(String title, String body) {
            this.title = title;
            this.body = body;
        }
    }
} 