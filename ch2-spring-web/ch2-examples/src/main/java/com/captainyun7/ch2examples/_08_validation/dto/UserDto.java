package com.captainyun7.ch2examples._08_validation.dto;

import jakarta.validation.constraints.*;

/**
 * 사용자 DTO 클래스
 * - Bean Validation 어노테이션을 사용한 입력값 검증
 */
public class UserDto {
    
    @NotNull(message = "ID는 필수 입력값입니다.")
    private Long id;
    
    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
    private String name;
    
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", 
             message = "비밀번호는 8자 이상이어야 하며, 영문, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.")
    private String password;
    
    @Min(value = 0, message = "나이는 0보다 커야 합니다.")
    @Max(value = 150, message = "나이는 150보다 작아야 합니다.")
    private int age;
    
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", 
             message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)")
    private String phoneNumber;
    
    // 기본 생성자
    public UserDto() {
    }
    
    // 생성자
    public UserDto(Long id, String name, String email, String password, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
    
    // Getter와 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
} 