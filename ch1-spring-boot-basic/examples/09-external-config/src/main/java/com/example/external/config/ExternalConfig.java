package com.example.external.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Validated
public class ExternalConfig {
    
    @NotEmpty
    private String name;
    
    private String description;
    
    @Valid
    @NotNull
    private Database db = new Database();
    
    @Valid
    @NotNull
    private Api api = new Api();
    
    @Getter
    @Setter
    public static class Database {
        @NotEmpty
        private String url;
        
        @NotEmpty
        private String username;
        
        private String password;
        
        private int poolSize = 10;
    }
    
    @Getter
    @Setter
    public static class Api {
        private String url;
        private String key;
        private int timeout = 30;
    }
} 