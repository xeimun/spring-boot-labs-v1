package com.captainyun7.ch2examples._07_restful_uri;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RESTful URI 설계 예제
 * - 리소스 중심 URI 설계
 * - HTTP 메서드를 통한 행위 표현
 * - 계층 구조를 반영한 URI 설계
 * - 쿼리 파라미터를 통한 필터링, 정렬, 페이징
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    // 메모리에 데이터 저장 (실제로는 DB를 사용해야 함)
    private final List<Book> books = new ArrayList<>();
    private long sequence = 0L;
    
    // 생성자 - 초기 데이터 설정
    public BookController() {
        books.add(new Book(++sequence, "스프링 부트 실전 활용", "김개발", "IT출판사", 
                LocalDate.of(2023, 1, 15), "programming"));
        books.add(new Book(++sequence, "Java 프로그래밍 마스터", "이자바", "코딩북스", 
                LocalDate.of(2022, 10, 20), "programming"));
        books.add(new Book(++sequence, "클라우드 네이티브 애플리케이션", "박클라우드", "기술서적", 
                LocalDate.of(2023, 3, 10), "technology"));
    }

    /**
     * 모든 책 조회 (필터링, 정렬, 페이징 지원)
     * GET /api/books?category=programming&sort=title&page=1&size=10
     */
    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        
        // 필터링
        List<Book> filteredBooks = books.stream()
                .filter(book -> category == null || book.getCategory().equals(category))
                .filter(book -> title == null || book.getTitle().contains(title))
                .collect(Collectors.toList());
        
        // 정렬
        if (sort.equals("title")) {
            filteredBooks.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));
        } else if (sort.equals("publishDate")) {
            filteredBooks.sort((b1, b2) -> b1.getPublishDate().compareTo(b2.getPublishDate()));
        }
        
        // 페이징
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, filteredBooks.size());
        
        if (fromIndex >= filteredBooks.size()) {
            return new ArrayList<>();
        }
        
        return filteredBooks.subList(fromIndex, toIndex);
    }

    /**
     * 특정 책 조회
     * GET /api/books/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 새 책 생성
     * POST /api/books
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        book.setId(++sequence);
        books.add(book);
        return book;
    }

    /**
     * 책 정보 수정
     * PUT /api/books/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return ResponseEntity.ok(updatedBook);
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 책 삭제
     * DELETE /api/books/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean removed = books.removeIf(book -> book.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * 특정 책의 리뷰 조회 (하위 리소스)
     * GET /api/books/{bookId}/reviews
     */
    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<List<Map<String, Object>>> getBookReviews(@PathVariable Long bookId) {
        // 책이 존재하는지 확인
        boolean bookExists = books.stream().anyMatch(book -> book.getId().equals(bookId));
        if (!bookExists) {
            return ResponseEntity.notFound().build();
        }
        
        // 샘플 리뷰 데이터 생성
        List<Map<String, Object>> reviews = new ArrayList<>();
        
        Map<String, Object> review1 = new HashMap<>();
        review1.put("id", 1);
        review1.put("rating", 5);
        review1.put("comment", "아주 좋은 책입니다!");
        review1.put("reviewer", "user123");
        
        Map<String, Object> review2 = new HashMap<>();
        review2.put("id", 2);
        review2.put("rating", 4);
        review2.put("comment", "유익한 내용이 많습니다.");
        review2.put("reviewer", "bookworm");
        
        reviews.add(review1);
        reviews.add(review2);
        
        return ResponseEntity.ok(reviews);
    }

    /**
     * 특정 책에 리뷰 추가 (하위 리소스)
     * POST /api/books/{bookId}/reviews
     */
    @PostMapping("/{bookId}/reviews")
    public ResponseEntity<Map<String, Object>> addBookReview(
            @PathVariable Long bookId,
            @RequestBody Map<String, Object> review) {
        
        // 책이 존재하는지 확인
        boolean bookExists = books.stream().anyMatch(book -> book.getId().equals(bookId));
        if (!bookExists) {
            return ResponseEntity.notFound().build();
        }
        
        // 리뷰 ID 생성 (실제로는 DB에서 생성)
        review.put("id", 3);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(review);
    }

    /**
     * 책 검색 (검색은 별도 엔드포인트로 분리)
     * GET /api/books/search?keyword=스프링
     */
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().contains(keyword) || 
                               book.getAuthor().contains(keyword))
                .collect(Collectors.toList());
    }
} 