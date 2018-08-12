package com.engineerproject.recommendationsystem.api;

import com.engineerproject.recommendationsystem.app.config.TestProperties;
import com.engineerproject.recommendationsystem.infrastructure.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TestEndpoint {

    private final ReviewRepository reviewRepository;
    private final TestProperties testProperties;

    @RequestMapping(value = "/test")
    private ResponseEntity<String> getString(Pageable pageable) {
        log.debug("go! go! go!");
        return ResponseEntity.ok(testProperties.getSelectedCategory());
    }
}
