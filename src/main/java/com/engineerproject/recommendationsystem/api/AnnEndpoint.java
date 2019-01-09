package com.engineerproject.recommendationsystem.api;

import com.engineerproject.recommendationsystem.app.ann.AnnService;
import com.engineerproject.recommendationsystem.app.ann.dto.PredictionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/ann")
public class AnnEndpoint {

    private final AnnService annService;

    @GetMapping("/learn")
    public ResponseEntity<Double> learn() {
        return ResponseEntity.ok(annService.learn());
    }

    @GetMapping("/predict/user/{userId}")
    public ResponseEntity<List<PredictionDTO>> predict(@PathVariable String userId) {
        return ResponseEntity.ok(annService.getPrediction(userId));
    }
}
