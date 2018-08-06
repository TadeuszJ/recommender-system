package com.engineerproject.recommendationsystem;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TestController {

    private final ReviewRepository reviewRepository;

    @RequestMapping(value = "/test")
    private ResponseEntity<String> getString(Pageable pageable) throws Exception {
        log.debug("go! go! go!");
        return ResponseEntity.ok(getClass().toString());
    }
}
