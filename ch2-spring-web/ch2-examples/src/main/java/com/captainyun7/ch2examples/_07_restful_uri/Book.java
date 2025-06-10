package com.captainyun7.ch2examples._07_restful_uri;

import java.time.LocalDate;

/**
 * 책 모델 클래스
 */
public class Book {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private LocalDate publishDate;
    private String category;
    
    // 기본 생성자
    public Book() {
    }
    
    // 생성자
    public Book(Long id, String title, String author, String publisher, LocalDate publishDate, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.category = category;
    }
    
    // Getter와 Setter
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
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public LocalDate getPublishDate() {
        return publishDate;
    }
    
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
} 