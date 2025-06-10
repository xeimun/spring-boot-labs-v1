package com.captainyun7.ch2examples._03_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    // 간단한 포스트 저장소 (인메모리)
    private final List<Post> posts = new ArrayList<>();
    
    // 생성자에서 초기 데이터 추가
    public MvcController() {
        posts.add(new Post(1L, "환영합니다", "Thymeleaf 예제입니다."));
        posts.add(new Post(2L, "Spring MVC", "스프링 MVC 컨트롤러 예제입니다."));
    }

    /**
     * ViewName을 반환하는 기본 컨트롤러
     */
    @GetMapping("/basic/view")
    public String basicView() {
        return "basic-view";  // src/main/resources/templates/basic-view.html을 찾음
    }
    
    /**
     * Model 객체에 데이터를 추가하여 뷰로 전달
     */
    @GetMapping("/model-example")
    public String modelExample(Model model) {
        model.addAttribute("message", "Thymeleaf에서 사용할 메시지입니다.");
        model.addAttribute("currentTime", java.time.LocalDateTime.now());
        
        // 복잡한 객체도 전달 가능
        Map<String, Object> user = new HashMap<>();
        user.put("name", "홍길동");
        user.put("email", "hong@example.com");
        model.addAttribute("user", user);
        
        return "model-example";  // src/main/resources/templates/model-example.html
    }
    
    /**
     * 포스트 목록 페이지 표시
     */
    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", posts);
        return "posts/list";  // src/main/resources/templates/posts/list.html
    }
    
    /**
     * 포스트 상세 페이지 표시
     */
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        // ID로 포스트 찾기
        Post post = findPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "posts/view";  // src/main/resources/templates/posts/view.html
        } else {
            return "redirect:/mvc/posts";
        }
    }
    
    /**
     * 포스트 작성 폼 표시
     */
    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/form";  // src/main/resources/templates/posts/form.html
    }
    
    /**
     * 포스트 저장 처리
     */
    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        // ID 설정 (실제로는 데이터베이스에서 자동 생성됨)
        Long newId = getNextId();
        post.setId(newId);
        
        // 포스트 저장
        posts.add(post);
        
        // 리다이렉트 시 메시지 전달
        redirectAttributes.addFlashAttribute("successMessage", "포스트가 성공적으로 저장되었습니다.");
        
        return "redirect:/mvc/posts";
    }
    
    /**
     * 포스트 수정 폼 표시
     */
    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = findPostById(id);
        if (post != null) {
            model.addAttribute("post", post);
            return "posts/form";  // 동일한 폼 템플릿 재사용
        } else {
            return "redirect:/mvc/posts";
        }
    }
    
    /**
     * 포스트 수정 처리
     */
    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post updatedPost, 
                               RedirectAttributes redirectAttributes) {
        Post post = findPostById(id);
        if (post != null) {
            post.setTitle(updatedPost.getTitle());
            post.setBody(updatedPost.getBody());
            redirectAttributes.addFlashAttribute("successMessage", "포스트가 성공적으로 수정되었습니다.");
        }
        return "redirect:/mvc/posts";
    }
    
    /**
     * 포스트 삭제 처리
     */
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Post post = findPostById(id);
        if (post != null) {
            posts.remove(post);
            redirectAttributes.addFlashAttribute("successMessage", "포스트가 성공적으로 삭제되었습니다.");
        }
        return "redirect:/mvc/posts";
    }
    
    // 헬퍼 메소드: ID로 포스트 찾기
    private Post findPostById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // 헬퍼 메소드: 다음 ID 생성
    private Long getNextId() {
        return posts.stream()
                .mapToLong(Post::getId)
                .max()
                .orElse(0) + 1;
    }
    
    /**
     * 포스트 데이터 모델 클래스
     */
    public static class Post {
        private Long id;
        private String title;
        private String body;
        
        public Post() {
        }
        
        public Post(Long id, String title, String body) {
            this.id = id;
            this.title = title;
            this.body = body;
        }
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getBody() {
            return body;
        }
        
        public void setBody(String body) {
            this.body = body;
        }
    }
} 