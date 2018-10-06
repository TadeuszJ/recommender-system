package com.engineerproject.recommendationsystem.api;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsCollector;
import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/neighbors")
public class NeighborsEndpoint {

    private final NeighborsCollector neighborsCollector;

    @GetMapping("/update/{id}")
    private ResponseEntity<NeighborsDTO> update(@PathVariable String id) {
        return ResponseEntity.ok(neighborsCollector.updateNeighbors(id));
    }
}
