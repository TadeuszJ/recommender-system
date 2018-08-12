package com.engineerproject.recommendationsystem.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {
    String activeUserId;
    String selectedCategory;
}
