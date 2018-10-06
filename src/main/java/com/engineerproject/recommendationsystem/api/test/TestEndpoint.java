package com.engineerproject.recommendationsystem.api.test;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsCollector;
import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TestEndpoint {

    private final NeighborsCollector neighborsCollector;

    @GetMapping(value = "/test")
    private ResponseEntity<NeighborsDTO> testMethod(Pageable pageable) {
        log.info("start test");
        return ResponseEntity.ok(neighborsCollector.updateNeighbors(TestConfig.ACTIVE_USER_ID));
    }
}
