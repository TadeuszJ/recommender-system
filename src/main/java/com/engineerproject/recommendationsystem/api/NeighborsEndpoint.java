package com.engineerproject.recommendationsystem.api;

import com.engineerproject.recommendationsystem.app.neighbors.NeighborsCollector;
import com.engineerproject.recommendationsystem.app.neighbors.dto.NeighborsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/neighbors")
public class NeighborsEndpoint {

    private final NeighborsCollector neighborsCollector;

    @GetMapping("/{id}/update")
    private ResponseEntity<NeighborsDTO> update(@PathParam("id") String id) {
        return ResponseEntity.ok(neighborsCollector.updateNeighbors(id));
    }

    @GetMapping("/{userId}/update/business/{businessId}")
    private ResponseEntity<NeighborsDTO> update(@PathParam("userId") String userId, @PathParam("businessId") String businessId) {
        return ResponseEntity.ok(neighborsCollector.updateNeighbors(userId));
    }
}
