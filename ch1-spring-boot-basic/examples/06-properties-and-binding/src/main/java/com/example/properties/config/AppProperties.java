package com.example.properties.config;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Validated
public class AppProperties {
    
    @NotEmpty
    private String name;
    
    private String description;
    
    private String version;
    
    private boolean enabled;
    
    private Duration timeout;
    
    private DataSize maxSize;
    
    private Server server;
    
    private List<String> listValues;
    
    private Map<String, String> mapValues;
    
    private Nested nested;
    
    @Getter
    @Setter
    public static class Server {
        private String host;
        private int port;
    }
    
    @Getter
    @Setter
    public static class Nested {
        private String name;
        private int value;
    }
} 